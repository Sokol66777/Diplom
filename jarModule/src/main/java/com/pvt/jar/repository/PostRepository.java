package com.pvt.jar.repository;

import com.pvt.jar.entity.Post;

import java.util.Optional;

public interface PostRepository extends BaseRepository<Post,Long>{


    Optional<Post> findByName(String name);
}
