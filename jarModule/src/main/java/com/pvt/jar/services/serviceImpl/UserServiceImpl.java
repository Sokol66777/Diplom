package com.pvt.jar.services.serviceImpl;

import com.pvt.jar.entity.User;
import com.pvt.jar.exceptions.UserLogicException;
import com.pvt.jar.services.UserService;
import com.pvt.jar.exceptions.LogicException;
import com.pvt.jar.repository.UserRepository;
import com.pvt.jar.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService<User,Long> implements UserService {

    @Autowired
    private UserRepository userRepositoryService;

    @Override
    public List<User> getAllUsers() {

        return userRepositoryService.findAll();
    }

    @Override
    public User getByUsername(String username) {

        return userRepositoryService.findByUsername(username).orElse(null);
    }

    @Override
    public User getByEmail(String email) {

        return userRepositoryService.findByEmail(email).orElse(null);
    }

    @Override
    public Page<User> findByUsernameLike(String username, Pageable pageable) {

        return userRepositoryService.findByUsernameLike(username,pageable);
    }


    @Override
    @Transactional
    public void add(User user) throws LogicException {

        User userCheck;


        userCheck = getByEmail(user.getEmail());
        if (userCheck != null) {
            throw new UserLogicException("this email used");
        }

        userCheck = getByUsername(user.getUsername());
        if (userCheck != null) {
            throw new UserLogicException("this username used");
        }
        userRepositoryService.save(user);
    }

    @Transactional
    @Override
    public void modify(User user) throws LogicException {

        User userCheck;
        User userOld = userRepositoryService.findById(user.getID()).orElse(null);

        if(!userOld.getUsername().equals(user.getUsername())){

            userCheck = userRepositoryService.findByUsername(user.getUsername()).orElse(null);
            if (userCheck != null) {
                throw new UserLogicException("this username used");
            }
        }

        if(!userOld.getEmail().equals(user.getEmail())){

            userCheck = userRepositoryService.findByEmail(user.getEmail()).orElse(null);
            if (userCheck != null) {
                throw new UserLogicException("this email used");
            }
        }
        userRepositoryService.save(user);
    }
}
