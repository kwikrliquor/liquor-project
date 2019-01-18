package com.example.springblog.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query("from Product a where a.user=?1")
    Product findUserId(int id);
    @Query("delete from Product a where a.id=?1")
    Product deleteById(int id);
    Product findByTitle(String title);
}
