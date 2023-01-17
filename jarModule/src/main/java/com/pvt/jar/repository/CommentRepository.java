package com.pvt.jar.repository;

import com.pvt.jar.entity.Comment;

import java.util.Optional;

public interface CommentRepository extends BaseRepository<Comment,Long>{

    Optional<Comment> findByName(String name);
}
