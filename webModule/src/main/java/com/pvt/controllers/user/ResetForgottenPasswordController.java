package com.pvt.controllers.user;

import com.pvt.fasad.EmailService;
import com.pvt.fasad.UserFasad;
import com.pvt.forms.UserForm;
import com.pvt.jar.exceptions.LogicException;
import com.pvt.jar.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/resetPassword")
public class ResetForgottenPasswordController {

    @Autowired
    UserFasad userFasad;

    @Autowired
    EmailService emailService;


    @GetMapping
    public ModelAndView redirectToReset(){

        ModelAndView modelAndView = new ModelAndView("resetPassword");
        return modelAndView;

    }

    @PostMapping
    public ModelAndView sendResetCode(@RequestParam("username") String username, HttpServletRequest request){

        UserForm userForm = userFasad.getByUsername(username);
        ModelAndView modelAndView;

        if(userForm!=null){

            modelAndView = new ModelAndView("inputResetCode");
            modelAndView.addObject("username",username);
            int resetCode = (int)(1000 + Math.random()*9000);
            request.getSession().setAttribute("resetPasswordCode",resetCode);
            emailService.sendEmail(userForm.getEmail(), userForm.getEmail(), "Reset code", "input this code in field "+resetCode);
        }else{

            modelAndView = new ModelAndView("resetPassword");
            modelAndView.addObject("errorMessage","user with this name not found");
        }
        return modelAndView;
    }

    @PostMapping("/checkResetCode")
    public ModelAndView checkResetCode(@RequestParam("resetCode") int resetCode,
                                       @RequestParam("username") String username, HttpServletRequest request){

        int trueResetCode = (int)request.getSession().getAttribute("resetPasswordCode");
        ModelAndView modelAndView;

        if(resetCode==trueResetCode){

            modelAndView = new ModelAndView("inputResetPassword");
            modelAndView.addObject("userForm",new UserForm());
            modelAndView.addObject("username",username);
            request.getSession().removeAttribute("resetPasswordCode");
        }else {
            modelAndView = new ModelAndView("inputResetCode");
            modelAndView.addObject("errorMessage","code is wrong");
            modelAndView.addObject("username",username);
        }

        return modelAndView;
    }

    @PostMapping("/setResetPassword")
    public ModelAndView setResetPassword(@ModelAttribute("userForm")UserForm userForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("inputResetPassword");
        if(userForm.getPassword().equals(userForm.getConfirmedPassword())){

            UserForm user = userFasad.getByUsername(userForm.getUsername());
            user.setPassword(userForm.getPassword());
            user.setNewPassword(userForm.getPassword());
            try {

                userFasad.update(user);
                response.sendRedirect(request.getContextPath()+"/user/welcome");
            } catch (LogicException e) {


                modelAndView.addObject("userForm",new UserForm());
                modelAndView.addObject("username",userForm.getUsername());
                modelAndView.addObject("errorMessage",e.getMessage());
            }
        }
        return modelAndView;
    }
}
