package ru.task.deliveryapp.core.application.usecases.commands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;
import ru.task.deliveryapp.core.ports.OrderRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CreateOrderHandlerIT {
    @Autowired
    private CreateOrderHandler handler;
    @Autowired
    private OrderRepository repository;

    @Test
    @Sql("/order_data.sql") // TODO customize!
    public void testHandle() {
        UUID basketId = UUID.fromString("e7c84de4-3261-476a-9481-fb6be211de7");
        String address = "Ломоносова 13";
        var weight = Weight.create(7);
        var command = new CreateOrderCommand(basketId, address, weight);
        handler.handle(command);
        Order order = repository.get(basketId);
        assertAll("Testing CreateOrder usecase",
                () -> assertNotNull(order),
                () -> assertEquals(basketId, order.getId()),
                () -> assertEquals(weight, order.getWeight())
        );
    }
}
