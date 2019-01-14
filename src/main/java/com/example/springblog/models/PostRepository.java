package com.example.springblog.models;

import com.example.springblog.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query("from Post a where a.user=?1")
    Post findUserId(int id);
    @Query("delete from Post a where a.id=?1")
    Post deleteById(int id);
    Post findByTitle(String title);
}
