package com.pvt.controllers.comment;

import com.pvt.fasad.CommentFasad;
import com.pvt.fasad.PostFasad;
import com.pvt.forms.CommentForm;
import com.pvt.forms.PostForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentFasad commentFasad;

    @Autowired
    PostFasad postFasad;

    @GetMapping("/commentsOfPost")
    public ModelAndView commentsOfPost(@RequestParam("idPost") long idPost){

        PostForm postForm = postFasad.get(idPost);
        List<CommentForm> commentForms = commentFasad.findByIdPost(idPost);

        ModelAndView modelAndView = new ModelAndView("commentsOfPost");
        modelAndView.addObject("comments",commentForms);
        modelAndView.addObject("post",postForm);
        modelAndView.addObject("newComment",new CommentForm());

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addComment(@ModelAttribute("newComment") CommentForm commentForm){
        return new ModelAndView();
    }
}
