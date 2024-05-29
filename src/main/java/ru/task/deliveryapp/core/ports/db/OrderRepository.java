package ru.task.deliveryapp.core.ports.db;

import ru.task.deliveryapp.core.domain.aggregate.order.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    Order add(Order order);
    void update(Order order);
    Order get(UUID orderId);
    List<Order> getAllAssigned();
    List<Order> getAllNotAssigned();
}
