package com.pvt.controllers.comment;

import com.pvt.fasad.CommentFasad;
import com.pvt.fasad.PostFasad;
import com.pvt.forms.CommentForm;
import com.pvt.forms.PostForm;
import com.pvt.jar.exceptions.LogicException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentFasad commentFasad;

    @Autowired
    private PostFasad postFasad;

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
    public ModelAndView addComment(@ModelAttribute("newComment") CommentForm commentForm, HttpServletResponse response,
                                   HttpServletRequest request) throws IOException {

        ModelAndView modelAndView = new ModelAndView("commentsOfPost");
        try {
            commentFasad.add(commentForm);
            response.sendRedirect(request.getContextPath()+"/comment/commentsOfPost?idPost="+commentForm.getIdPost());
        } catch (LogicException e) {

            modelAndView.addObject("comments",commentFasad.findByIdPost(commentForm.getIdPost()));
            modelAndView.addObject("post",postFasad.get(commentForm.getIdPost()));
            modelAndView.addObject("newComment",new CommentForm());
            modelAndView.addObject("errorMessage", e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/delete")
    public void delete(@RequestParam("idComment") long idComment, @RequestParam("idPost") long idPost, HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        commentFasad.delete(idComment);
        response.sendRedirect(request.getContextPath()+"/comment/commentsOfPost?idPost="+idPost);
    }

    @GetMapping("/update")
    public ModelAndView preUpdate(@RequestParam("idComment") long idComment){

        ModelAndView modelAndView = new ModelAndView("updateComment");
        modelAndView.addObject("updateComment", commentFasad.get(idComment));
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("updateComment") CommentForm commentForm,HttpServletResponse response,
                               HttpServletRequest request) throws IOException {

        ModelAndView modelAndView=null;
        CommentForm updateCommentForm = commentFasad.get(commentForm.getId());
        updateCommentForm.setText(commentForm.getText());

        try {
            commentFasad.update(updateCommentForm);
            response.sendRedirect(request.getContextPath()+"/comment/commentsOfPost?idPost="+updateCommentForm.getIdPost());
        } catch (LogicException e) {

            modelAndView = new ModelAndView("updateComment");
            modelAndView.addObject("errorMessage",e.getMessage());
            modelAndView.addObject("updateComment",commentFasad.get(commentForm.getId()));
        }

        return modelAndView;
    }
}
