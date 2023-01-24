package com.pvt.jar.repository;

import com.pvt.jar.entity.User;

import java.util.Optional;


public interface UserRepository extends BaseRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
