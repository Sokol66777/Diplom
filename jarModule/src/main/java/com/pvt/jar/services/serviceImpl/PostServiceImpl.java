package com.pvt.jar.services.serviceImpl;

import com.pvt.jar.entity.Post;
import com.pvt.jar.exceptions.LogicException;
import com.pvt.jar.repository.PostRepository;
import com.pvt.jar.services.BaseService;
import com.pvt.jar.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl extends BaseService<Post,Long> implements PostService {

    @Autowired
    private PostRepository postRepositoryService;

    @Override
    public List<Post> getAll() {

        return postRepositoryService.findAll();
    }

    @Override
    public Post getByName(String name) {

        return postRepositoryService.findByName(name).orElse(null);
    }

    @Override
    public List<Post> findPostsByIdUser(long idUser) {

        return postRepositoryService.findPostsByIdUser(idUser);
    }

    @Override
    public List<Post> findAllOrderByCreateDateDesc() {

        return postRepositoryService.findAllOrderByCreateDateDesc();
    }

    @Transactional
    @Override
    public void add(Post post) throws LogicException {

        postRepositoryService.save(post);
    }

    @Override
    @Transactional
    public void modify(Post post) throws LogicException{

        postRepositoryService.save(post);
    }




}
