package com.example.springblog.repo;

import com.example.springblog.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("from Product a where a.user=?1")
    Product findUserId(long id);
    @Query("delete from Product a where a.id=?1")
    Product deleteById(long id);
    Product findByTitle(String title);
}
