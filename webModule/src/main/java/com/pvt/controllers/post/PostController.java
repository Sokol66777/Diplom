package com.pvt.controllers.post;

import com.pvt.fasad.PostFasad;
import com.pvt.forms.PostForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostFasad postFasad;

    @GetMapping("/myPosts")
    public ModelAndView myPosts(@ModelAttribute("idUserPost") long idUser){

        List<PostForm> postForms = postFasad.findMyPosts(idUser);
        ModelAndView modelAndView = new ModelAndView("loggedUserPost");
        modelAndView.addObject("myPosts",postForms);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView getAddPost(){

        ModelAndView modelAndView = new ModelAndView("addPost");
        modelAndView.addObject("addPostForm",new PostForm());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView postAddPost(){
        return null;
    }
}
