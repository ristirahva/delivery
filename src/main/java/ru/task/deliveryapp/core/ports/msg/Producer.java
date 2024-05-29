package ru.task.deliveryapp.core.ports.msg;

import ru.task.deliveryapp.core.domain.aggregate.order.Order;

public interface Producer {
    /**
     * Sending notifications about order status changes.
     *
     * @param order
     */
    void sendEvents(Order order);
}
