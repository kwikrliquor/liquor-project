package com.example.springblog.repo;

import com.example.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);

  User findByEmail(String email);
}
