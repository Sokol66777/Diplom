package com.pvt.jar.services;

import com.pvt.jar.entity.User;

import java.util.List;

public interface UserService extends IService<User,Long>{

    List<User> getAllUsers() ;

    User getByUsername(String username);

    User getByEmail(String email) ;


}
