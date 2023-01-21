package com.pvt.forms;


import com.pvt.jar.entity.Comment;
import com.pvt.jar.entity.Post;
import lombok.*;
import org.hibernate.LazyInitializationException;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PostForm {

    private long id;
    private String name;
    private String text;
    private long idUser;
    private MultipartFile fileData;
    private byte[] image;
    private String username;
    private Set<CommentForm> comments = new HashSet<>();

    public PostForm(Post post){

        this.id=post.getID();
        this.name= post.getName();
        this.text= post.getText();
        this.idUser=post.getUser().getID();
        this.image= post.getImage();
        this.username=post.getUser().getUsername();
        try{
            for(Comment comment : post.getComments()){
                CommentForm commentForm = new CommentForm(comment);
                this.comments.add(commentForm);
            }
        }catch (LazyInitializationException e){
            this.comments = new HashSet<>();
        }
    }
}
