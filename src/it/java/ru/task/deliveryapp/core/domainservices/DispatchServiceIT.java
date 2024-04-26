package ru.task.deliveryapp.core.domainservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.ports.CourierRepository;
import ru.task.deliveryapp.core.ports.OrderRepository;
import ru.task.deliveryapp.infrastructure.adapters.postgres.repository.OrderJpaRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DispatchServiceIT {
    @Autowired
    DispatchService dispatchService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CourierRepository courierRepository;

    @Test
    @Sql("/uc_assign_orders_data.sql")
    public void testDispatch() {
        var a = orderRepository.getAllNotAssigned();

        Order order = orderRepository.get(UUID.fromString("f2959d3e-aadd-470c-9530-c60f3843300a"));
        List<Courier> courierList = courierRepository.getAllReady();
        var chosenCourier = dispatchService.dispatch(order, courierList);
        assertAll("Testing dispatch() method",
                () -> assertEquals(UUID.fromString("407adabd-dc4e-405b-9ee9-c324154a36c4"), chosenCourier.getId()),
                () -> assertEquals("Courier 2", chosenCourier.getName())
        );
    }
}
