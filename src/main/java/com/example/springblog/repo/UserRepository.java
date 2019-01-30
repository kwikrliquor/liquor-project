package com.example.springblog.repo;

import com.example.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  User findById(long id);
  User findByUsername(String username);
  User findByEmail(String email);

  @Query("from User a where a.ageIsVerified=false")
  List <User> findUnverifiedUsers();



}
