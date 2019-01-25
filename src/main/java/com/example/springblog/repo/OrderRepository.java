package com.example.springblog.repo;

        import com.example.springblog.models.Order;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;

        import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findOrderById(long id);

    Order findByOrderStatusId(long id);

    Order findOrderByOrderStatusId(long id);

    @Query("from Order a where a.orderStatusId.id=?1")
    Order findOrdersByOrderStatusId(long id);

}
