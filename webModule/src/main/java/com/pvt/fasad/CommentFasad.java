package com.pvt.fasad;

import com.pvt.forms.CommentForm;
import com.pvt.jar.entity.Comment;
import com.pvt.jar.exceptions.LogicException;
import com.pvt.jar.services.CommentService;
import com.pvt.jar.services.PostService;
import com.pvt.jar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentFasad {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    public Comment buildComment(CommentForm commentForm){

        Comment comment = new Comment();
        comment.setID(commentForm.getId());
        comment.setName(commentForm.getName());
        comment.setText(commentForm.getText());
        comment.setUser(userService.get(commentForm.getIdUser()));
        comment.setPost(postService.get(commentForm.getIdPost()));
        return comment;
    }

    public void delete(long id){

        commentService.delete(id);
    }

    public CommentForm get(long id){

        return new CommentForm(commentService.get(id));
    }

    public void add(CommentForm commentForm) throws LogicException {

        Comment comment = buildComment(commentForm);
        commentService.add(comment);
    }

    public void update(CommentForm commentForm) throws LogicException {

        Comment comment = buildComment(commentForm);
        commentService.modify(comment);
    }
}
