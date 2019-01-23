package com.example.springblog.repo;

import com.example.springblog.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("from Product a where a.user=?1")
    Product findUserId(int id);
    @Query("delete from Product a where a.id=?1")
    Product deleteById(int id);
    @Query("select a from Product a where a.category=1")
    List<Product> findBeer();
    @Query("from Product a where a.category=2")
    List<Product> findWine();
    @Query("from Product a where a.category=3")
    List<Product> findLiquor();
}
