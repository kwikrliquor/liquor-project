package com.example.springblog.repo;

import com.example.springblog.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRole(@Param("role") String role);
}
