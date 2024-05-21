package ru.task.deliveryapp.core.application.usecases.commands;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.ports.CourierRepository;
import ru.task.deliveryapp.core.ports.OrderRepository;

@Service
public class MoveToOrderHandler {

    private static final Logger log = LoggerFactory.getLogger(MoveToOrderHandler.class);

    private final CourierRepository courierRepository;

    private OrderRepository orderRepository;

    @Autowired
    public MoveToOrderHandler(CourierRepository courierRepository, OrderRepository orderRepository) {
        this.courierRepository = courierRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    // запуск по расписанию убран, чтобы не мешать тестам
    //@Scheduled(fixedDelay = 10000)
    public void handle() {
        log.info("Moving assigned couriers");
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
