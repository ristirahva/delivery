package ru.task.deliveryapp.core.application.usecases.commands;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.ports.CourierRepository;
import ru.task.deliveryapp.core.ports.OrderRepository;

@Service
public class MoveToOrderHandler {

    private final CourierRepository courierRepository;

    private OrderRepository orderRepository;

    @Autowired
    public MoveToOrderHandler(CourierRepository courierRepository, OrderRepository orderRepository) {
        this.courierRepository = courierRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void handle() {
        for (var order : orderRepository.getAllAssigned()) {
            var courier = courierRepository.get(order.getCourierId());
            courier.move(order.getLocation());
            if (courier.getLocation().equals(order.getLocation())) {
                order.complete();
                courier.completeOrder();
                orderRepository.update(order);
            }
            courierRepository.update(courier);
        }
    }
}
