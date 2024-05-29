package ru.task.deliveryapp.infrastructure.adapters.kafka;

import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;

import java.util.UUID;

public record OrderIntegrationEvent(UUID id, UUID orderId, OrderStatus orderStatus) {
}
