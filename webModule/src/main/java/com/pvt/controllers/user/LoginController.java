package com.pvt.controllers.user;


import com.pvt.fasad.EmailService;
import com.pvt.fasad.PostFasad;
import com.pvt.fasad.UserFasad;
import com.pvt.forms.PostForm;
import com.pvt.forms.UserForm;
import com.pvt.jar.entity.Post;
import com.pvt.jar.entity.User;
import com.pvt.jar.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private UserFasad userFasad;


    @Autowired
    private PostFasad postFasad;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/"})
    public ModelAndView startApp(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return new ModelAndView("startPage");
    }

    @PostMapping(value = {"/loginS"})
    public ModelAndView welcome(@ModelAttribute("loginForm") UserForm loginForm, HttpServletRequest request,
                                @PageableDefault(size = 3,sort = {"ID"},direction = Sort.Direction.DESC) Pageable pageable){

        UserForm user;
        ModelAndView modelAndView;

        user=userFasad.getByUsername(loginForm.getUsername());
        Page<Post> allPosts = postFasad.findByHideFalse(pageable);

        modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("allPosts",allPosts.getContent());
        modelAndView.addObject("totalPages",allPosts.getTotalPages());

        request.getSession().setAttribute("user", user);

        return modelAndView;
    }

}
