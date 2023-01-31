package com.pvt.jar.repository;

import com.pvt.jar.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends BaseRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Page<User> findByUsernameLike(String username, Pageable pageable);

    Page<User> findByIDNot(long id, Pageable pageable);
}
