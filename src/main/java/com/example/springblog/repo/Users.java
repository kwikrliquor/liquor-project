package com.example.springblog.repo;

import com.example.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<User, Long> {
  User findById(long id);
  User findByUsername(String username);
  User findByEmail(String email);
}
