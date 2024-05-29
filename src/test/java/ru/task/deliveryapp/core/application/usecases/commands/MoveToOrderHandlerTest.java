package ru.task.deliveryapp.core.application.usecases.commands;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.courier.Transport;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;
import ru.task.deliveryapp.core.ports.db.CourierRepository;
import ru.task.deliveryapp.core.ports.db.OrderRepository;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class MoveToOrderHandlerTest {

    private OrderRepository orderRepository = mock(OrderRepository.class);
    private CourierRepository courierRepository = mock(CourierRepository.class);

    @Test
    public void testHandle() {
        MoveToOrderHandler handler = new MoveToOrderHandler(courierRepository, orderRepository);
        var courier = Courier.create("Courier", Transport.CAR);
        var order = Order.create(UUID.randomUUID(), Location.create(1, 2), Weight.create(2));
        order.assign(courier);
        courier.startWork();
        courier.inWork();
        var orderList = new ArrayList<Order>();
        orderList.add(order);
        when(orderRepository.getAllAssigned()).thenReturn(orderList);
        when(courierRepository.get(order.getCourierId())).thenReturn(courier);
        handler.handle();
        verify(orderRepository, times(1)).getAllAssigned();
        verify(courierRepository, times(1)).get(any(UUID.class));
        verify(orderRepository, times(1)).update(order);
        verify(courierRepository, times(1)).update(courier);
    }
}
