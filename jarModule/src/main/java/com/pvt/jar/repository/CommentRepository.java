package com.pvt.jar.repository;

import com.pvt.jar.entity.Comment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends BaseRepository<Comment,Long>{

    Optional<Comment> findByName(String name);

    @Query("select c from Comment as c where c.post.id=?1")
    List<Comment> findByIdPost(long idPost);
}
