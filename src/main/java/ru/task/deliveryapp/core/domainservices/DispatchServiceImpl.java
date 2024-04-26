package ru.task.deliveryapp.core.domainservices;

import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;

import java.util.Comparator;
import java.util.List;

@Service
public class DispatchServiceImpl implements DispatchService {
    @Override
    public Courier dispatch(Order order, List<Courier> listCouriers) {
        var courier = listCouriers.stream().min(Comparator.comparing(c->c.calculateTimeToPoint(order.getLocation()))).get();
        return courier;
    }
}
