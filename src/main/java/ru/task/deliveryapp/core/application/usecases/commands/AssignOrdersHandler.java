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

    private final DispatchService dispatchService;

    private final OrderRepository orderRepository;

    private final CourierRepository courierRepository;

    @Autowired
    public AssignOrdersHandler(DispatchService dispatchService, OrderRepository orderRepository, CourierRepository courierRepository) {
        this.dispatchService = dispatchService;
        this.orderRepository = orderRepository;
        this.courierRepository = courierRepository;
    }

    @Transactional
    public void handle() {
        var listCouriersReady = courierRepository.getAllReady();
        for (var order : orderRepository.getAllNotAssigned()) {
            if (listCouriersReady.isEmpty()) {
                break;
            }
            var listCouriersAccepted = listCouriersReady
                    .stream()
                    .filter(courier -> courier.getTransport().getCapacity().compareTo(order.getWeight()) >= 0)
                    .toList();
            if (!listCouriersAccepted.isEmpty()) {
                var suitableCourier = dispatchService.dispatch(order, listCouriersAccepted);
                suitableCourier.inWork();
                order.assign(suitableCourier);
                courierRepository.update(suitableCourier);
                orderRepository.update(order);
                listCouriersReady.remove(suitableCourier);
            }
        }
    }
}
