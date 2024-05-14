package ru.task.deliveryapp.infrastructure.adapters.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.infrastructure.adapters.postgres.entity.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    private static final Logger log = LoggerFactory.getLogger(OrderMapper.class);

    public static Order toDomain(OrderEntity entity) {
        return Order.create(entity.getId(), entity.getCourierId(), entity.getStatus(), entity.getLocation(), entity.getWeight());
    }

    public static List<Order> listToDomain(List<OrderEntity> orderEntityList) {
        return orderEntityList.stream().map(entity -> OrderMapper.toDomain(entity)).collect(Collectors.toList());
    }

    public static OrderEntity toEntity(Order order) {
        log.info("Mapping order: " + order);
        return new OrderEntity(order.getId(), order.getCourierId(), order.getStatus(), order.getLocation(), order.getWeight());
    }
}
