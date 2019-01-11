package com.example.springblog;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query("from Post a where a.id=?1")
    Post findById(int id);
    @Query("delete from Post a where a.id=?1")
    Post deleteById(int id);
    Post findByTitle(String title);
}
