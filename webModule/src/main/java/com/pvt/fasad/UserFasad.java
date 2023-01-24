package com.pvt.fasad;

import com.pvt.forms.CommentForm;
import com.pvt.forms.PostForm;
import com.pvt.forms.UserForm;
import com.pvt.jar.entity.Comment;
import com.pvt.jar.entity.Post;
import com.pvt.jar.entity.User;
import com.pvt.jar.exceptions.LogicException;
import com.pvt.jar.exceptions.UserLogicException;
import com.pvt.jar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.pvt.jar.validation.ValidationUsersParametrs.validationPassword;

@Component
public class UserFasad {

    @Autowired
    private UserService userService;

    public User buildUser(UserForm userForm){

        User user = new User();
        user.setID(userForm.getId());
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setRole(userForm.getRole());
        user.setPassword(userForm.getPassword());
        user.setImage(userForm.getImage());

        Set<User> subscribers = new HashSet<>();
        for(UserForm subscriberUserForm:userForm.getSubscribers()){
            User subscriber = userService.get(subscriberUserForm.getId());
            subscribers.add(subscriber);
        }
        user.setSubscribers(subscribers);

        Set<User> subscriptions = new HashSet<>();
        for(UserForm subscriptionUserForm:userForm.getSubscriptions()){
            User subscription = userService.get(subscriptionUserForm.getId());
            subscriptions.add(subscription);
        }
        user.setSubscriptions(subscriptions);

        return user;
    }

    public void delete(long id){
        userService.delete(id);
    }

    public UserForm get(long id){

        return new UserForm(userService.get(id));
    }

    public void update(UserForm userForm) throws LogicException {

        if(userForm.getNewPassword()!=null) {
            try {
                validationPassword(userForm.getPassword());
            } catch (UserLogicException e) {
                throw new LogicException(e.getMessage());
            }

            userForm.setPassword(BCrypt.hashpw(userForm.getPassword(), BCrypt.gensalt(10)));
        }

        User user = buildUser(userForm);
        userService.modify(user);
    }

    public void addUser(UserForm userForm) throws LogicException {

        try{
            validationPassword(userForm.getPassword());
        }catch (UserLogicException e){
            throw new LogicException(e.getMessage());
        }

        userForm.setPassword(BCrypt.hashpw(userForm.getPassword(), BCrypt.gensalt(10)));
        User user = buildUser(userForm);
        userService.add(user);
    }

    public UserForm getByUsername(String username){

        UserForm userForm;
        User user = userService.getByUsername(username);

        if(user!=null) {
            userForm = new UserForm(user);
        }else {
            userForm=null;
        }

        return userForm;
    }

    public void subscribe(long idChanel, long idUser) throws LogicException {

        User user = userService.get(idUser);
        User chanel = userService.get(idChanel);
        Set<User> subscriptions = user.getSubscriptions();
        subscriptions.add(chanel);
        user.setSubscriptions(subscriptions);
        userService.modify(user);
    }

    public void unsubscribe(long idChanel, long idUser) throws LogicException {

        User user = userService.get(idUser);
        User chanel = userService.get(idChanel);
        Set<User> subscriptions = user.getSubscriptions();
        subscriptions.remove(chanel);
        user.setSubscriptions(subscriptions);
        userService.modify(user);
    }

    public List<UserForm> getAllUsers(){

        List<User> users=userService.getAllUsers();
        List<UserForm> userForms = new ArrayList<>();
        for(User user:users){
            userForms.add(new UserForm(user));
        }
        return userForms;
    }
}
