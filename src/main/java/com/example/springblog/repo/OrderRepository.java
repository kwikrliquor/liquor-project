package com.example.springblog.repo;

import com.example.springblog.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findOrderById(long id);
}
