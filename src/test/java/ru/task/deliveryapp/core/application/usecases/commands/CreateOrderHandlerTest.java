package ru.task.deliveryapp.core.application.usecases.commands;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;
import ru.task.deliveryapp.core.domainservices.DispatchService;
import ru.task.deliveryapp.core.ports.CourierRepository;
import ru.task.deliveryapp.core.ports.OrderRepository;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class CreateOrderHandlerTest {

    private OrderRepository orderRepository = mock(OrderRepository.class);

    @Test
    public void testHandle() {
        var basketId = UUID.fromString("e7c84de4-3261-476a-9481-fb6be211de7");
        var address = "Ломоносова 13";
        var weight = Weight.create(7);
        var command = new CreateOrderCommand(basketId, address, weight);

        CreateOrderHandler handler = new CreateOrderHandler(orderRepository);
        handler.handle(command);
        verify(orderRepository, times(1)).add(any(Order.class));
    }
}
