package com.example.springblog.repo;

import com.example.springblog.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    OrderStatus findByStatus(String status);

    OrderStatus findOrderStatusById(long id);

    OrderStatus findById(long id);

}
