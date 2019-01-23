package com.example.springblog.repo;

import com.example.springblog.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findById(long id);
}
