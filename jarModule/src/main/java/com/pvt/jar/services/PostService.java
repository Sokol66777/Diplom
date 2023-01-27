package com.pvt.jar.services;

import com.pvt.jar.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService extends IService<Post,Long> {

    public List<Post> getAll();

    public Post getByName(String name);

    Page<Post> findPostsByIdUser(long idUser,Pageable pageable);

    List<Post> findAllOrderByCreateDateDesc();

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByHideFalse(Pageable pageable);

    Page<Post> findByIdUserAndHideFalse(long idUser,Pageable pageable);

}
