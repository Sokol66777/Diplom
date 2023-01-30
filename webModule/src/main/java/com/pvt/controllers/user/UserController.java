package com.pvt.controllers.user;

import com.pvt.fasad.PostFasad;
import com.pvt.fasad.UserFasad;
import com.pvt.forms.PostForm;
import com.pvt.forms.UserForm;
import com.pvt.jar.entity.Post;
import com.pvt.jar.entity.SubscribeRequest;
import com.pvt.jar.entity.User;
import com.pvt.jar.exceptions.LogicException;
import com.pvt.jar.services.SubscribeRequestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFasad userFasad;

    @Autowired
    private PostFasad postFasad;

    @Autowired
    private SubscribeRequestService subscribeRequestService;

    @GetMapping("/welcome")
    public ModelAndView welcome(@PageableDefault(size = 3,sort = {"ID"},direction = Sort.Direction.DESC) Pageable pageable){

        Page<Post> allPosts = postFasad.findByHideFalse(pageable);
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("allPosts",allPosts.getContent());
        modelAndView.addObject("totalPages",allPosts.getTotalPages());

        return modelAndView;
    }

    @GetMapping(value = {"/logout"})
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().invalidate();
        ModelAndView modelAndView = new ModelAndView("startPage");
        return modelAndView;
    }

    @GetMapping(value = {"/allUsers"})
    public ModelAndView allUsers(HttpServletRequest request,HttpServletResponse response) throws IOException {

        UserForm adminForm =(UserForm) request.getSession().getAttribute("user");
        List<UserForm> trueUsers = new ArrayList<>();
        List<UserForm> userForms;
        userForms = userFasad.getAllUsers();

        if(userForms!=null) {
            for (UserForm user : userForms) {
                if (user.getId()!=adminForm.getId()) {
                    trueUsers.add(user);
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("allUsers",trueUsers);
        return modelAndView;
    }
    @GetMapping(value = {"/delete"})
    public void deleteUser(@ModelAttribute("deleteUsersID") long id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        userFasad.delete(id);
        response.sendRedirect(request.getContextPath()+"/user/allUsers");
    }

    @GetMapping(value = {"/update"})
    public ModelAndView redirectToUpdate(@ModelAttribute("updateUsersID") long updateUsersID){

        UserForm updateUserForm = userFasad.get(updateUsersID);

        ModelAndView modelAndView = new ModelAndView("updateUser");
        modelAndView.addObject("updateUserForm",updateUserForm);
        modelAndView.addObject("imageForm",new UserForm());

        return modelAndView;
    }

    @PostMapping(value = {"/update"})
    public ModelAndView updateUser(@ModelAttribute("updateUser") UserForm updateUserForm, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {

        UserForm user = userFasad.get(updateUserForm.getId());
        UserForm imageForm = (UserForm) request.getSession().getAttribute("imageForm");
        ModelAndView modelAndView= new ModelAndView("updateUser");;

        if(imageForm!=null){
            user.setImage(imageForm.getImage());
            request.getSession().removeAttribute("imageForm");
        }
        user.setEmail(updateUserForm.getNewEmail());
        user.setUsername(updateUserForm.getNewUsername());
        if(updateUserForm.getNewPassword()!=null && !updateUserForm.getNewPassword().equals("")) {

             user.setPassword(updateUserForm.getNewPassword());
             user.setNewPassword(updateUserForm.getNewPassword());
        }

        try{

            userFasad.update(user);
            UserForm userFromSession = (UserForm) request.getSession().getAttribute("user");
            if(userFromSession.getId()==updateUserForm.getId()){

                request.getSession().setAttribute("user", user);
            }
            response.sendRedirect(request.getContextPath()+"/user/welcome");
        } catch (LogicException e) {

            modelAndView.addObject("errorMassage", e.getMessage());
            modelAndView.addObject("updateUserForm",userFasad.get(updateUserForm.getId()));
            modelAndView.addObject("imageForm",new UserForm());
            return modelAndView;
        }

        return modelAndView ;
    }

    @GetMapping("/friendUser")
    public ModelAndView sendToFriendUserPage(@RequestParam("idFriendUser") long idFriendUser, HttpServletRequest request,
                                             HttpServletResponse response) throws IOException {

        UserForm loggedUser = (UserForm) request.getSession().getAttribute("user");

        if(loggedUser.getId()==idFriendUser){
            response.sendRedirect(request.getContextPath()+"/user/welcome");
        }
        UserForm friendUserForm = userFasad.get(idFriendUser);
        boolean isSubscribe = false;

        for(UserForm userForm: loggedUser.getSubscriptions()){
            if(userForm.getId()==idFriendUser){
                isSubscribe=true;
                break;
            }
        }

        ModelAndView modelAndView = new ModelAndView("friendUser");
        modelAndView.addObject("isSubscribe",isSubscribe);
        modelAndView.addObject("friendUser",friendUserForm);
        return modelAndView;

    }

    @PostMapping("/subscribe")
    public void subscribe(@ModelAttribute("subRequest")SubscribeRequest subscribeRequest,
                          HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(subscribeRequest.isStatus()){
            try {
                userFasad.subscribe(subscribeRequest.getIdChanel(),subscribeRequest.getIdSubscriber());
                UserForm currentUser = userFasad.get(subscribeRequest.getIdChanel());
                request.getSession().setAttribute("user",currentUser);
                subscribeRequestService.delete(subscribeRequest.getId());
                response.sendRedirect(request.getContextPath()+"/subscribeRequest/currentUserNotifications?idUser="+subscribeRequest.getIdChanel());
            } catch (LogicException e) {
                response.sendRedirect(request.getContextPath()+"/subscribeRequest/currentUserNotifications?idUser="+subscribeRequest.getIdChanel());
            }
        }else {
            subscribeRequestService.delete(subscribeRequest.getId());
            response.sendRedirect(request.getContextPath()+"/subscribeRequest/currentUserNotifications?idUser="+subscribeRequest.getIdChanel());

        }


    }

    @GetMapping("/unsubscribe")
    public void unsubscribe(@RequestParam ("idChanel") long idChanel, @RequestParam("idUser") long idUser,
                            HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            userFasad.unsubscribe(idChanel,idUser);
            UserForm currentUser = userFasad.get(idUser);
            request.getSession().setAttribute("user",currentUser);
            response.sendRedirect(request.getContextPath()+"/user/friendUser?idFriendUser="+idChanel);
        } catch (LogicException e) {
            response.sendRedirect(request.getContextPath()+"/user/friendUser?idFriendUser="+idChanel);
        }
    }

    @PostMapping(value = {"/uploadPhoto"})
    public ModelAndView uploadPhoto(@ModelAttribute("imageForm") UserForm imageForm, HttpServletRequest request) throws IOException {

        ModelAndView modelAndView;
        UserForm userForm = (UserForm)request.getSession().getAttribute("user");
        String filename=imageForm.getFileData().getOriginalFilename();

        if(!filename.equals("")){
            imageForm.setImage(imageForm.getFileData().getBytes());
            request.getSession().setAttribute("imageForm",imageForm);
        }

        if(userForm!=null){
            modelAndView = new ModelAndView("updateUser");
            modelAndView.addObject("updateUserForm",userForm);
        }else{
            userForm=new UserForm();
            modelAndView = new ModelAndView("addUser");
            modelAndView.addObject("registrationForm",userForm);
        }

        if(!filename.equals("")){
            modelAndView.addObject("message", "file is loaded");
        }

        return modelAndView;
    }

    @GetMapping(value = {"/viewImage"})
    public void viewImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserForm imageForm = (UserForm) request.getSession().getAttribute("imageForm");
        if(imageForm!=null && imageForm.getImage()!=null) {

            response.setContentType("image/jpg");
            response.getOutputStream().write(imageForm.getImage());
        }
        response.getOutputStream().close();
    }

    @GetMapping(value = {"/imageOnWelcomePage"})
    public void imageOnWelcomePage(@RequestParam("idUser") long idUser, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        UserForm userForm = userFasad.get(idUser);
        if (userForm.getImage() != null) {

            response.setContentType("img/jpg");
            response.getOutputStream().write(userForm.getImage());
        }
        response.getOutputStream().close();
    }

    @GetMapping(value = {"/preSearchUser"})
    public ModelAndView redirectToSearchUser(){

        ModelAndView modelAndView = new ModelAndView("searchUser");
        return modelAndView;
    }

    @PostMapping(value = {"/searchUser"})
    public ModelAndView searchUser(@RequestParam("username")String username,
                                   @PageableDefault(size = 3,sort = {"ID"},direction = Sort.Direction.DESC) Pageable pageable){

        ModelAndView modelAndView = new ModelAndView("searchUser");
        Page<User> findUsers = userFasad.findByUsernameLike(username,pageable);
        modelAndView.addObject("findUsers",findUsers.getContent());
        modelAndView.addObject("totalPages",findUsers.getTotalPages());
        return modelAndView;
    }

}
