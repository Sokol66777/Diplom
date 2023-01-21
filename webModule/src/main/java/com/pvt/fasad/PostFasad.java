package com.pvt.fasad;

import com.pvt.forms.CommentForm;
import com.pvt.forms.PostForm;
import com.pvt.jar.entity.Comment;
import com.pvt.jar.entity.Post;
import com.pvt.jar.exceptions.LogicException;
import com.pvt.jar.services.PostService;
import com.pvt.jar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PostFasad {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    public Post buildPost(PostForm postForm){

        Post post = new Post();
        post.setID(postForm.getId());
        post.setName(postForm.getName());
        post.setText(postForm.getText());
        post.setUser(userService.get(postForm.getIdUser()));
        post.setImage(postForm.getImage());
        Set<Comment> comments= new HashSet<>();
        for(CommentForm commentForm: postForm.getComments()){

            Comment comment = new Comment();
            comment.setID(commentForm.getId());
            comment.setName(commentForm.getName());
            comment.setText(commentForm.getText());
            comment.setUser(userService.get(commentForm.getIdUser()));
            comment.setPost(postService.get(commentForm.getIdPost()));
            comments.add(comment);
        }
        post.setComments(comments);

        return post;
    }

    public PostForm get(long id){

        return new PostForm(postService.get(id));
    }

    public void delete(long id){

        postService.delete(id);
    }

    public void add(PostForm postForm) throws LogicException {

        postService.add(buildPost(postForm));
    }

    public void update(PostForm postForm) throws LogicException {

        postService.modify(buildPost(postForm));
    }

    public List<PostForm> findPostsByIdUser(long idUser){

        List<PostForm> postForms = new ArrayList<>();
        List<Post> posts = postService.findPostsByIdUser(idUser);

        for(Post post: posts){
            postForms.add(new PostForm(post));
        }

        return postForms;
    }

    public List<PostForm> findAll(){

        List<PostForm> postForms = new ArrayList<>();
        List<Post> posts = postService.getAll();

        for(Post post: posts){
            postForms.add(new PostForm(post));
        }
        return postForms;
    }

    public List<PostForm> findAllOrderByCreateDateDesc(){

        List<PostForm> postForms = new ArrayList<>();
        List<Post> posts = postService.findAllOrderByCreateDateDesc();

        for(Post post: posts){
            postForms.add(new PostForm(post));
        }
        return postForms;
    }
}
