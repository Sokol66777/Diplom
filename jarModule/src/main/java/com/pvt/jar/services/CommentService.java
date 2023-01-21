package com.pvt.jar.services;

import com.pvt.jar.entity.Comment;

import java.util.List;

public interface CommentService extends IService<Comment,Long>{

    public List<Comment> getAll();

    public Comment getByName(String name);

    List<Comment> findByIdPost(long idPost);
}
