package com.example.springblog.repo;

import com.example.springblog.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("from User a where a.id=?1")
    User findById(long id);
    User findByUsername(String username);
    User findByEmail(String email);
}
