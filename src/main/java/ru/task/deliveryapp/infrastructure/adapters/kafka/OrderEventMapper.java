package ru.task.deliveryapp.infrastructure.adapters.kafka;

import ru.task.deliveryapp.core.domain.aggregate.order.events.OrderDomainEvent;

public class OrderEventMapper {
    public static OrderIntegrationEvent toIntegrationEvent(OrderDomainEvent orderDomainEvent) {
        return new OrderIntegrationEvent(orderDomainEvent.id(), orderDomainEvent.orderId(), orderDomainEvent.orderStatus());
    }
}
