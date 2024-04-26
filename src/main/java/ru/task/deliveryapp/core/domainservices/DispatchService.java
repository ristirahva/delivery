package ru.task.deliveryapp.core.domainservices;

import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;

import java.util.List;

public interface DispatchService {
    Courier dispatch(Order order, List<Courier> listCouriers);
}
