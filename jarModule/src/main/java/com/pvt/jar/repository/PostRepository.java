package com.pvt.jar.repository;

import com.pvt.jar.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends BaseRepository<Post,Long>{


    Optional<Post> findByName(String name);

    @Query("select p from Post as p where p.user.id=?1 order by p.createDate Desc")
    Page<Post> findPostsByIdUser(long idUser,Pageable pageable);

    @Query("from Post as p order by p.createDate Desc")
    List<Post> findAllOrderByCreateDateDesc();

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByHideFalse(Pageable pageable);

    @Query("select p from Post as p where p.user.id=?1 and p.hide=false")
    Page<Post> findByIdUserAndHideFalse(long idUser,Pageable pageable);
}
