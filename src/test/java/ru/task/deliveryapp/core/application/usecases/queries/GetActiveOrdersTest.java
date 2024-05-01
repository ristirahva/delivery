package ru.task.deliveryapp.core.application.usecases.queries;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.infrastructure.adapters.postgres.repository.OrderJpaRepository;

import static org.mockito.Mockito.*;

public class GetActiveOrdersTest {

    private OrderJpaRepository orderJpaRepository = mock(OrderJpaRepository.class);

    @Test
    public void testHandle() {
        var getActiveOrders = new GetActiveOrders(orderJpaRepository);
        getActiveOrders.handle();
        verify(orderJpaRepository, times(1)).findByStatusNot(OrderStatus.COMPLETED);
    }
}
