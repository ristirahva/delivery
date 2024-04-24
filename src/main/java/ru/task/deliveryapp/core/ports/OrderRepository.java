package ru.task.deliveryapp.core.ports;

import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.infrastructure.adapters.postgres.entity.OrderEntity;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    Order add(Order order);
    void update(Order order);
    Order get(UUID orderId);
    List<Order> getAllAssigned();
    List<Order> getAllNotAssigned();
}
