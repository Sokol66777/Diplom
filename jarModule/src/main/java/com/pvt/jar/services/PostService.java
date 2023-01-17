package com.pvt.jar.services;

import com.pvt.jar.entity.Post;

import java.util.List;

public interface PostService extends IService<Post,Long> {

    public List<Post> getAll();

    public Post getByName(String name);

}
