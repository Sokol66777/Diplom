package com.pvt.forms;

import com.pvt.jar.entity.Comment;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CommentForm {

    private long id;
    private String name;
    private String username;
    private String text;
    private long idUser;
    private long idPost;
    private Date createDate;
    private Date updateDate;


    public CommentForm(Comment comment){
        this.id=comment.getID();
        this.name= comment.getName();
        this.text= comment.getText();
        this.idUser=comment.getUser().getID();
        this.idPost=comment.getPost().getID();
        this.username=comment.getUser().getUsername();
        this.createDate=comment.getCreateDate();
        this.updateDate=comment.getUpdateDate();
    }

}
