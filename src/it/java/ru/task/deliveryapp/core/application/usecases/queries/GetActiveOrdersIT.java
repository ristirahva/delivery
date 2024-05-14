package ru.task.deliveryapp.core.application.usecases.queries;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class GetActiveOrdersIT {
    @Autowired
    GetActiveOrders getActiveOrders;

    @Test
    @Sql("/uc_get_active_orders_data.sql")
    public void testHandle() {
        List<Order> listActiveOrders = getActiveOrders.handle();
        assertAll("Testing GetActiveOrders use case",
                () -> assertNotNull(listActiveOrders),
                () -> assertEquals(3, listActiveOrders.size())
        );
    }
}
