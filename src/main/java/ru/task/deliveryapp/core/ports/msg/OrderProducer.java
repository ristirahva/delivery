package ru.task.deliveryapp.core.ports.msg;

import ru.task.deliveryapp.core.domain.aggregate.order.Order;

public interface OrderProducer {
    /**
     * Sending notifications about events.
     */
    void sendEvents();
}
