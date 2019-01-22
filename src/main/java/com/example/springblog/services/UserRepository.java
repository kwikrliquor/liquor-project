package com.example.springblog.services;

import com.example.springblog.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);
  User findById(Long id);
}
