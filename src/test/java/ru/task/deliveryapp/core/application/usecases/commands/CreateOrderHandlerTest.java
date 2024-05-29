package ru.task.deliveryapp.core.application.usecases.commands;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;
import ru.task.deliveryapp.core.ports.db.OrderRepository;
import ru.task.deliveryapp.infrastructure.adapters.grpc.fwoservice.GeoClient;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class CreateOrderHandlerTest {

    private OrderRepository orderRepository = mock(OrderRepository.class);
    private GeoClient geoClient = mock(GeoClient.class);

    @Test
    public void testHandle() {
        var basketId = UUID.fromString("e7c84de4-3261-476a-9481-fb6be211de7");
        var address = "Ломоносова 13";
        var weight = Weight.create(7);
        var command = new CreateOrderCommand(basketId, address, weight);

        when(geoClient.getLocation(anyString())).thenReturn(Location.create(1, 1));
        CreateOrderHandler handler = new CreateOrderHandler(geoClient, orderRepository);
        handler.handle(command);
        verify(orderRepository, times(1)).add(any(Order.class));
    }
}
