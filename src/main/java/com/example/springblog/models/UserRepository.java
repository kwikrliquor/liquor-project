package com.example.springblog.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("from User a where a.id=?1")
    User findById(int id);
}
