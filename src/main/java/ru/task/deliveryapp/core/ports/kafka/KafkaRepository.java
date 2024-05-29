package ru.task.deliveryapp.core.ports.kafka;

import ru.task.deliveryapp.core.domain.aggregate.order.Order;

public interface KafkaRepository {
    /**
     * Sending notifications about order status changes.
     *
     * @param order
     */
    void sendEvents(Order order);
}
