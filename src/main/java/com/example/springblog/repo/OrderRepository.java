package com.example.springblog.repo;

        import com.example.springblog.models.Order;
        import com.example.springblog.models.User;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;

        import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    @Query("from Order a where a.userId.id = id")
//    List<Order> findOrder(long id);

    List<Order> findAllByUserId(Long id);

    Order findByUserId(long id);

    Order findOrdersById(long id);

    @Query("from Order a where a.orderStatusId.id=1")
    List<Order> findOrdersStatus1();

    @Query("from Order a where a.orderStatusId.id=2")
    List<Order> findOrdersStatus2();

    @Query("from Order a where a.orderStatusId.id=3")
    List<Order> findOrdersStatus3();
}
