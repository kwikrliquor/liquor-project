package com.example.springblog.repo;

import com.example.springblog.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    OrderStatus findByStatus(String status);

    OrderStatus findOrderStatusById(long id);

    OrderStatus findById(long id);

    @Query("from OrderStatus a where a.id=1")
    OrderStatus findStatusOrderPlaced();

    @Query("from OrderStatus a where a.id=2")
    OrderStatus findStatusOrderPrepared();

    @Query("from OrderStatus a where a.id=2")
    OrderStatus findStatusOrderBackPrepared();

    @Query("from OrderStatus a where a.id=3")
    OrderStatus findStatusOrderDelivery();

    @Query("from OrderStatus a where a.id=4")
    OrderStatus findStatusOrderCompleted();

    @Query("from OrderStatus a where a.id=5")
    OrderStatus findStatusOrderPendingVerification();

}
