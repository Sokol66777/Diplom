package com.pvt.jar.services.serviceImpl;

import com.pvt.jar.entity.Comment;
import com.pvt.jar.exceptions.LogicException;
import com.pvt.jar.repository.CommentRepository;
import com.pvt.jar.services.BaseService;
import com.pvt.jar.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl extends BaseService<Comment,Long> implements CommentService {

    @Autowired
    private CommentRepository commentRepositoryService;

    @Override
    public List<Comment> getAll() {

        return commentRepositoryService.findAll();
    }

    @Override
    public Comment getByName(String name) {

        return commentRepositoryService.findByName(name).orElse(null);
    }

    @Override
    public List<Comment> findByIdPost(long idPost) {

        return commentRepositoryService.findByIdPost(idPost);
    }

    @Transactional
    @Override
    public void add(Comment comment) throws LogicException {


        commentRepositoryService.save(comment);
    }

    @Override
    @Transactional
    public void modify(Comment comment) throws LogicException{

        commentRepositoryService.save(comment);
    }

}
