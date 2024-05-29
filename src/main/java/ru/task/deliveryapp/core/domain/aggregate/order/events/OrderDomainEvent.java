package ru.task.deliveryapp.core.domain.aggregate.order.events;

import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.core.domain.event.DomainEvent;

import java.util.UUID;

public record OrderDomainEvent(UUID id, UUID orderId, OrderStatus orderStatus) implements DomainEvent {
}
