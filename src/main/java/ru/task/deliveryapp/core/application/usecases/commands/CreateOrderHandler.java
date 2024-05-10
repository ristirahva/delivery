package ru.task.deliveryapp.core.application.usecases.commands;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.core.ports.OrderRepository;

@Service
public class CreateOrderHandler {
    private static final Logger log = LoggerFactory.getLogger(CreateOrderHandler.class);

    private final OrderRepository repository;

    @Autowired
    public CreateOrderHandler(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void handle(CreateOrderCommand command) {
        log.info("Handling order: " + command);
        var order = Order.create(command.basketId(), null, OrderStatus.CREATED, Location.create(9, 9), command.weight());
        repository.add(order);
    }
}
