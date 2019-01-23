package com.example.springblog.repo;

import com.example.springblog.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query("from Product a where a.user=?1")
    Product findUserId(int id);
    @Query("delete from Product a where a.id=?1")
    Product deleteById(int id);
    @Query("from Product a where a.category=?1")
    Product findBeer(long id);
    @Query("from Product a where a.category=?2")
    Product findWine(long id);
    @Query("from Product a where a.category=?3")
    Product findLiquor(long id);
}
