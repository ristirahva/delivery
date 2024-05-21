package ru.task.deliveryapp.core.domainservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;

import java.util.Comparator;
import java.util.List;

@Service
public class DispatchServiceImpl implements DispatchService {

    private static final Logger log = LoggerFactory.getLogger(DispatchServiceImpl.class);

    @Override
    public Courier dispatch(Order order, List<Courier> listCouriers) {
        log.warn("Couriers list: {}", listCouriers);
        var courier = listCouriers.stream().min(Comparator.comparing(c->c.calculateTimeToPoint(order.getLocation()))).get();
        log.error("Order {} could be assigned to courier {}", order.getId(), courier.getName());
        return courier;
    }
}
