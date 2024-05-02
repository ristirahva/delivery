package ru.task.deliveryapp.core.application.usecases.commands;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.courier.Transport;
import ru.task.deliveryapp.core.ports.CourierRepository;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class StartWorkHandlerTest {

    private CourierRepository courierRepository = mock(CourierRepository.class);

    @Test
    public void testHandle() {
        var handler = new StartWorkHandler(courierRepository);
        var courierId = UUID.fromString("bf79a004-56d7-4e5f-a21c-0a9e5e08d10d");
        var courier = Courier.create("Courier 1", Transport.BICYCLE);
        var command = new StartWorkCommand(courierId);
        when(courierRepository.get(courierId)).thenReturn(courier);
        handler.handle(command);
        verify(courierRepository, times(1)).get(courierId);
        verify(courierRepository, times(1)).update(any(Courier.class));
    }
}
