package ru.task.deliveryapp.infrastructure.adapters.postgres;

import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.infrastructure.adapters.postgres.entity.OrderEntity;

public class OrderMapper {
    public static Order toDomain(OrderEntity entity) {
        return Order.create(entity.getId(), entity.getCourierId(), entity.getStatus(), entity.getLocation(), entity.getWeight());
    }

    public static OrderEntity toEntity(Order order) {
        return new OrderEntity(order.getId(), order.getCourierId(), order.getStatus(), order.getLocation(), order.getWeight());
    }
}
