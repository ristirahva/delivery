package ru.task.deliveryapp.core.application.usecases.commands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.ports.db.CourierRepository;
import ru.task.deliveryapp.core.ports.db.OrderRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class AssignOrdersHandlerIT {
    @Autowired
    AssignOrdersHandler handler;

    @Autowired
    CourierRepository courierRepository;

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Sql("/uc_assign_orders_data.sql")
    public void testHandle() {
        handler.handle();
        var listReadyCouriers = courierRepository.getAllReady();
        var listBusyCouriers = courierRepository.getAllBusy();
        var listOrdersNotAssigned = orderRepository.getAllNotAssigned();
        var listOrdersAssigned = orderRepository.getAllAssigned();
        assertAll("Testing AssignOrders use case",
                () -> assertEquals(1, listReadyCouriers.size()),
                () -> assertEquals(3, listBusyCouriers.size()),
                () -> assertEquals(3, listOrdersAssigned.size()),
                () -> assertEquals(2, listOrdersNotAssigned.size())
        );
    }
}
