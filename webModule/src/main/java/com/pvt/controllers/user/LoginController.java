package com.pvt.controllers.user;


import com.pvt.fasad.EmailService;
import com.pvt.fasad.PostFasad;
import com.pvt.fasad.UserFasad;
import com.pvt.forms.PostForm;
import com.pvt.forms.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    UserFasad userFasad;


    @Autowired
    PostFasad postFasad;

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
        List<PostForm> allPosts = postFasad.findAllOrderByCreateDateDesc();

        modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("allPosts",allPosts);

        request.getSession().setAttribute("user", user);

        return modelAndView;
    }

}
