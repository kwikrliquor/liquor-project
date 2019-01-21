package com.example.springblog.repo;

import com.example.springblog.models.User;
import org.springframework.data.repository.CrudRepository;

public interface Users extends CrudRepository<User, Long> {
        User findById(long id);
        User findByUsername(String username);
        User findByEmail(String email);
}
