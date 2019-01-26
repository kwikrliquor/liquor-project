package com.example.springblog.repo;

import com.example.springblog.models.OrderStatus;
import com.example.springblog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    OrderStatus findByStatus(String status);

    OrderStatus findOrderStatusById(int id);

    OrderStatus findById(int id);

    @Query("from OrderStatus a where a.id=1")
    OrderStatus findId1();
}
