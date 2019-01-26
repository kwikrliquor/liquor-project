package com.example.springblog.repo;

        import com.example.springblog.models.Order;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;

        import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(long id);

    Order findByOrderStatusId(long id);

    Order findOrderByOrderStatusId(long id);

    @Query("from Order a where a.orderStatusId.id=1")
    List<Order> findOrdersStatus1();

    @Query("from Order a where a.orderStatusId.id=2")
    List<Order> findOrdersStatus2();

}
