package ru.task.deliveryapp.core.application.usecases.commands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.core.ports.CourierRepository;
import ru.task.deliveryapp.core.ports.OrderRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class MoveToOrderHandlerIT {
    @Autowired
    private MoveToOrderHandler handler;

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Sql("/uc_move_to_order_data.sql")
    public void testHandle() {
        handler.handle();
        var courierBusyList =  courierRepository.getAllBusy();
        var courierReadyList = courierRepository.getAllReady();
        var orderAssignedList = orderRepository.getAllAssigned();
        var orderNotAssignedList = orderRepository.getAllNotAssigned();
        assertAll("Testing MoveToOrder use case",
                () -> assertNotNull(courierBusyList),
                () -> assertEquals(2, courierBusyList.size()),
                () -> assertEquals("Courier2", courierBusyList.get(0).getName()),
                () -> assertEquals(Location.create(2, 7), courierBusyList.get(0).getLocation()),
                () -> assertEquals("Courier3", courierBusyList.get(1).getName()),
                () -> assertEquals(Location.create(1, 3), courierBusyList.get(1).getLocation()),
                () -> assertNotNull(courierReadyList),
                () -> assertEquals(1, courierReadyList.size()),
                () -> assertEquals("Courier1", courierReadyList.get(0).getName()),
                () -> assertEquals(Location.create(5, 7), courierReadyList.get(0).getLocation()),
                () -> assertNotNull(orderAssignedList),
                () -> assertEquals(2, orderAssignedList.size()),
                () -> assertEquals(UUID.fromString("29f92517-e38c-4579-b957-a16b576be2bc"), orderAssignedList.get(0).getId()),
                () -> assertEquals(UUID.fromString("3a5c19de-072a-4ad3-b303-afb2c8b74e93"), orderAssignedList.get(1).getId()),
                () -> assertNotNull(orderNotAssignedList),
                () -> assertEquals(1, orderNotAssignedList.size()),
                () -> assertEquals(UUID.fromString("49ee1822-6703-4103-a2c6-9b2592a3f7f6"), orderNotAssignedList.get(0).getId())
        );


    }
}
