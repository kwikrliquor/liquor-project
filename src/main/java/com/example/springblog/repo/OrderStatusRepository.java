package com.example.springblog.repo;

import com.example.springblog.models.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {
    OrderStatus findByStatus(String status);

    OrderStatus findOrderStatusById(long id);
}
