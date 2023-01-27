package com.pvt.controllers.post;

import com.pvt.fasad.PostFasad;
import com.pvt.fasad.UserFasad;
import com.pvt.forms.PostForm;
import com.pvt.forms.UserForm;
import com.pvt.jar.entity.Post;
import com.pvt.jar.exceptions.LogicException;
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
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostFasad postFasad;

    @Autowired
    UserFasad userFasad;

    @GetMapping("/myPosts")
    public ModelAndView myPosts(@ModelAttribute("idUserPost") long idUser,
                                @PageableDefault(size = 3,sort = {"ID"},direction = Sort.Direction.DESC) Pageable pageable){

        Page<Post> posts = postFasad.findPostsByIdUser(idUser,pageable);
        ModelAndView modelAndView = new ModelAndView("loggedUserPost");
        modelAndView.addObject("myPosts",posts.getContent());
        modelAndView.addObject("totalPages",posts.getTotalPages());
        return modelAndView;
    }

    @GetMapping("/postsOfFriend")
    public ModelAndView postsOfFriend(@RequestParam("idFriendUser") long idFriendUser, HttpServletRequest request,
                                      @PageableDefault(size = 3,sort = {"ID"},direction = Sort.Direction.DESC) Pageable pageable){

        Page<Post> posts;
        UserForm userForm = (UserForm)request.getSession().getAttribute("user");

        if(userForm.getRole().equals("admin")){
            posts = postFasad.findPostsByIdUser(idFriendUser,pageable);
        }else {
            posts = postFasad.findByIdUserAndHideFalse(idFriendUser, pageable);
        }

        ModelAndView modelAndView = new ModelAndView("postsOfFriend");
        modelAndView.addObject("postsOfFriend",posts.getContent());
        modelAndView.addObject("totalPages",posts.getTotalPages());
        modelAndView.addObject("idFriendUser",idFriendUser);
        modelAndView.addObject("usernameOfFriend",userFasad.get(idFriendUser).getUsername());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView getAddPost(){

        ModelAndView modelAndView = new ModelAndView("addPost");
        modelAndView.addObject("addPostImageForm",new PostForm());
        modelAndView.addObject("addPostForm",new PostForm());
        return modelAndView;
    }

    @PostMapping(value = {"/add"})
    public ModelAndView addPost(@ModelAttribute(value = "addPostForm") PostForm addPostForm, HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        ModelAndView modelAndView= new ModelAndView("addPost");;
        UserForm userForm = (UserForm) request.getSession().getAttribute("user");
        addPostForm.setIdUser(userForm.getId());
        PostForm imageForm = (PostForm) request.getSession().getAttribute("imageForm");
        if(imageForm!=null) {
            addPostForm.setImage(imageForm.getImage());
            request.getSession().removeAttribute("imageForm");
        }

        try{
            postFasad.add(addPostForm);
            response.sendRedirect(request.getContextPath()+"/post/myPosts?idUserPost="+addPostForm.getIdUser());
        } catch (LogicException e) {

            modelAndView.addObject("addPostImageForm",new PostForm());
            modelAndView.addObject("addPostForm", new PostForm());
            modelAndView.addObject("errorMassage","Incorrect name or password");
        }

        return modelAndView;
    }

    @GetMapping(value = {"/delete"})
    public void deletePost(@RequestParam(value = "idPost") long idPost,
                           HttpServletResponse response, HttpServletRequest request) throws IOException {
        PostForm postForm = postFasad.get(idPost);
        postFasad.delete(idPost);
        UserForm userForm = (UserForm) request.getSession().getAttribute("user");

        if(postForm.getIdUser()==userForm.getId()) {
            response.sendRedirect(request.getContextPath() + "/post/myPosts?idUserPost=" + userForm.getId());
        }else{
            response.sendRedirect(request.getContextPath() + "/post/postsOfFriend?idFriendUser=" + postForm.getIdUser());
        }
    }


    @PostMapping(value = {"/uploadPhoto"})
    public ModelAndView uploadPhoto(@ModelAttribute("addPostImageForm") PostForm imageForm,
                                    @RequestParam(required = false,value = "idPost",defaultValue = "0") long idPost,
                                    HttpServletRequest request) throws IOException {

        ModelAndView modelAndView;
        String filename=imageForm.getFileData().getOriginalFilename();

        if(!filename.equals("")){
            imageForm.setImage(imageForm.getFileData().getBytes());
            request.getSession().setAttribute("imageForm",imageForm);
        }

        if(idPost==0){
            PostForm postForm=new PostForm();
            modelAndView = new ModelAndView("addPost");
            modelAndView.addObject("addPostForm",postForm);
        }else{

            PostForm postForm = postFasad.get(idPost);
            modelAndView = new ModelAndView("updatePost");
            modelAndView.addObject("updatePostForm",postForm);
            modelAndView.addObject("imageForm",imageForm);
        }

        if(!filename.equals("")){
            modelAndView.addObject("message", "file is loaded");
        }

        return modelAndView;
    }

    @GetMapping(value = {"/viewPostImage"})
    public void imageOnWelcomePage(@RequestParam("idPost") long idPost, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PostForm postForm = postFasad.get(idPost);
        if (postForm.getImage() != null) {

            response.setContentType("img/jpg");
            response.getOutputStream().write(postForm.getImage());
        }
        response.getOutputStream().close();
    }

    @GetMapping("/update")
    public ModelAndView preUpdatePost(@RequestParam("idPost") long id){

        PostForm postForm = postFasad.get(id);
        ModelAndView modelAndView = new ModelAndView("updatePost");
        modelAndView.addObject("updatePostForm", postForm);
        modelAndView.addObject("imageForm",new PostForm());
        return modelAndView;
    }

    @PostMapping(value = {"/update"})
    public ModelAndView updatePost(@ModelAttribute("updatePostForm") PostForm updatePostForm, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        PostForm postForm = postFasad.get(updatePostForm.getId());
        ModelAndView modelAndView = new ModelAndView("updatePost");
        PostForm imageForm =(PostForm) request.getSession().getAttribute("imageForm");

        if(imageForm!=null){
            postForm.setImage(imageForm.getImage());
            request.getSession().removeAttribute("imageForm");
        }
        postForm.setHide(updatePostForm.isHide());
        postForm.setName(updatePostForm.getName());
        postForm.setText(updatePostForm.getText());

        try {

            postFasad.update(postForm);
            response.sendRedirect(request.getContextPath()+"/post/myPosts?idUserPost="+postForm.getIdUser());
        } catch (LogicException e) {
            modelAndView.addObject("updatePostForm",updatePostForm);
            modelAndView.addObject("errorMassage",e.getMessage());
        }
        return modelAndView;
    }
}
