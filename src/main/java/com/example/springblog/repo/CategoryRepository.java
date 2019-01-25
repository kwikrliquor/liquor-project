package com.example.springblog.repo;

import com.example.springblog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findByName(String name);
}
