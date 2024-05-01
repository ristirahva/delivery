package ru.task.deliveryapp.core.application.usecases.queries;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.infrastructure.adapters.postgres.repository.CourierJpaRepository;

import static org.mockito.Mockito.*;

public class GetAllCouriersTest {

    private CourierJpaRepository courierJpaRepository = mock(CourierJpaRepository.class);

    @Test
    public void testHandle() {
        var getAllCouriers = new GetAllCouriers(courierJpaRepository);
        getAllCouriers.handle();
        verify(courierJpaRepository, times(1)).findAll();
    }
}
