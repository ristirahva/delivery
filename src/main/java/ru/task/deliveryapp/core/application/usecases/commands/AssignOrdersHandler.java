package ru.task.deliveryapp.core.application.usecases.commands;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domainservices.DispatchService;
import ru.task.deliveryapp.core.ports.CourierRepository;
import ru.task.deliveryapp.core.ports.OrderRepository;

@Service
public class AssignOrdersHandler {

    @Autowired
    DispatchService dispatchService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CourierRepository courierRepository;

    @Transactional
    public void handle() {
        var listCouriersReady = courierRepository.getAllReady();
        for (Order order : orderRepository.getAllNotAssigned()) {
            if (listCouriersReady.isEmpty()) {
                break;
            }
            var assignedCourier = dispatchService.dispatch(order, listCouriersReady);
            assignedCourier.inWork();
            order.assign(assignedCourier);
            courierRepository.update(assignedCourier);
            orderRepository.update(order);
            listCouriersReady.remove(assignedCourier);
        }
    }
}
