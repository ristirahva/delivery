package ru.task.deliveryapp.core.application.usecases.commands;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.courier.Transport;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;
import ru.task.deliveryapp.core.domainservices.DispatchService;
import ru.task.deliveryapp.core.ports.db.CourierRepository;
import ru.task.deliveryapp.core.ports.db.OrderRepository;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class AssignOrdersHandlerTest {

    private OrderRepository orderRepository = mock(OrderRepository.class);
    private CourierRepository courierRepository = mock(CourierRepository.class);
    private DispatchService dispatcherService = mock(DispatchService.class);

    @Test
    public void testHandle() {
        var order = Order.create(UUID.randomUUID(), Location.create(1, 2), Weight.create(2));
        var orderList = new ArrayList<Order>();
        orderList.add(order);

        var courier = Courier.create("Courier 1", Transport.BICYCLE);
        courier.startWork();

        var courierList = new ArrayList<Courier>();
        courierList.add(courier);

        AssignOrdersHandler handler = new AssignOrdersHandler(dispatcherService, orderRepository, courierRepository);
        when(orderRepository.getAllNotAssigned()).thenReturn(orderList);
        when(courierRepository.getAllReady()).thenReturn(courierList);
        when(dispatcherService.dispatch(any(Order.class), anyList())).thenReturn(courier);
        handler.handle();
        verify(courierRepository, times(1)).getAllReady();
        verify(orderRepository, times(1)).getAllNotAssigned();
        verify(dispatcherService, times(1)).dispatch(any(Order.class), anyList());
        verify(courierRepository, times(1)).update(courier);
        verify(orderRepository, times(1)).update(order);
    }
}
