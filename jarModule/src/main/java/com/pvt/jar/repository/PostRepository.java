package com.pvt.jar.repository;

import com.pvt.jar.entity.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends BaseRepository<Post,Long>{


    Optional<Post> findByName(String name);

    @Query("select p from Post as p where p.user.id=?1 order by p.createDate Desc")
    List<Post> findPostsByIdUser(long idUser);

    @Query("from Post as p order by p.createDate Desc")
    List<Post> findAllOrderByCreateDateDesc();

}
