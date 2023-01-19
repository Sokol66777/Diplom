package com.pvt.controllers.user;


import com.pvt.fasad.EmailService;
import com.pvt.fasad.UserFasad;
import com.pvt.forms.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    UserFasad userFasad;

    @Autowired
    EmailService emailService;

    @RequestMapping(value = {"/"})
    public ModelAndView startApp(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("startPage");
        return modelAndView;
    }

    @PostMapping(value = {"/loginS"})
    public ModelAndView welcome(@ModelAttribute("loginForm") UserForm loginForm, HttpServletRequest request){

        UserForm user;
        ModelAndView modelAndView;


        user=userFasad.getByUsername(loginForm.getUsername());


        modelAndView = new ModelAndView("welcome");

        if(user.getImage()!=null){

            request.getSession().setAttribute("imageForm",user);
        }

        request.getSession().setAttribute("user", user);


        emailService.sendEmail("Sokol66777@mail.ru", user.getEmail(), "login", "user "+user.getUsername()+" login in APP");
        return modelAndView;
    }

}
