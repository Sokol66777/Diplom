package com.pvt.jar.services;

import com.pvt.jar.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService extends IService<User,Long>{

    List<User> getAllUsers() ;

    User getByUsername(String username);

    User getByEmail(String email) ;

    Page<User> findByUsernameLike(String username, Pageable pageable);

    Page<User> findByIDNot(long id, Pageable pageable);

}
