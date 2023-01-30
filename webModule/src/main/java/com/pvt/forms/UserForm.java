package com.pvt.forms;


import com.pvt.jar.entity.Comment;
import com.pvt.jar.entity.Post;
import com.pvt.jar.entity.User;
import lombok.*;
import org.hibernate.LazyInitializationException;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"posts","comments"})
public class UserForm {

    private long id;
    private String username;
    private String newUsername;
    private String password;
    private String email;
    private String role;
    private String confirmedPassword;
    private String newPassword;
    private String newEmail;
    private MultipartFile fileData;
    private byte[] image;
    private Set<PostForm> posts = new HashSet<>();
    private Set<CommentForm> comments = new HashSet<>();
    private Set<UserForm> subscribers = new HashSet<>();
    private Set<UserForm> subscriptions = new HashSet<>();

    public UserForm(User user){

        this.password = user.getPassword();
        this.username = user.getUsername();
        this.id = user.getID();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.image= user.getImage();

        try{

            for(Post post: user.getPosts()){
                PostForm postForm = new PostForm(post);
                this.posts.add(postForm);
            }
        }catch (LazyInitializationException e){
            this.posts=new HashSet<>();
        }

        try{

            for(Comment comment: user.getComments()){
                CommentForm commentForm = new CommentForm(comment);
                this.comments.add(commentForm);
            }
        }catch (LazyInitializationException e){
            this.comments=new HashSet<>();
        }

        try{
            for(User subscriberUser: user.getSubscribers()){
                UserForm userForm = new UserForm();
                userForm.setId(subscriberUser.getID());
                userForm.setUsername(subscriberUser.getUsername());
                userForm.setPassword(subscriberUser.getPassword());
                userForm.setEmail(subscriberUser.getEmail());
                userForm.setRole(subscriberUser.getRole());
                userForm.setImage(subscriberUser.getImage());
                this.subscribers.add(userForm);
            }
        }catch (LazyInitializationException e){
            this.subscribers=new HashSet<>();
        }

        try{
            for(User subscriptionUser: user.getSubscriptions()){
                UserForm userForm = new UserForm();
                userForm.setId(subscriptionUser.getID());
                userForm.setUsername(subscriptionUser.getUsername());
                userForm.setPassword(subscriptionUser.getPassword());
                userForm.setEmail(subscriptionUser.getEmail());
                userForm.setRole(subscriptionUser.getRole());
                userForm.setImage(subscriptionUser.getImage());
                this.subscriptions.add(userForm);
            }
        }catch (LazyInitializationException e){
            this.subscriptions=new HashSet<>();
        }

    }

    @Override
    public String toString() {
        return
                        "username=" + username +
                        ", email=" + email +
                        ", role=" + role  ;
    }

}
