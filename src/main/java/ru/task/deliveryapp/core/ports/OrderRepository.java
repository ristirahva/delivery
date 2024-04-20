package ru.task.deliveryapp.core.ports;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.task.deliveryapp.core.domain.orderaggregate.Order;

import java.util.Collection;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "SELECT * FROM delivery_order o WHERE o.status<>1", nativeQuery = true)
    Collection<Order> getAllNotAssigned();

    @Query(value = "SELECT * FROM delivery_order o WHERE o.status=1", nativeQuery = true)
    Collection<Order> getAllAssigned();
}
