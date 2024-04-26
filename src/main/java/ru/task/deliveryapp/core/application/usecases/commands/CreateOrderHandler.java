package ru.task.deliveryapp.core.application.usecases.commands;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.core.ports.OrderRepository;

@Service
public class CreateOrderHandler {
    @Autowired
    private OrderRepository repository;

    @Transactional
    public void handle(CreateOrderCommand command) {
        Order order = Order.create(command.getBasketId(), null, OrderStatus.CREATED, Location.create(9, 9), command.getWeight());
        repository.add(order);
    }
}
