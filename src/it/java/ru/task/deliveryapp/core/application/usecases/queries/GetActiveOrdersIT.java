package ru.task.deliveryapp.core.application.usecases.queries;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.core.usecases.queries.GetActiveOrders;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GetActiveOrdersIT {
    @Autowired
    GetActiveOrders getActiveOrders;

    @Test
    @Sql("/uc_get_active_orders_data.sql")
    public void testHandle() {
        List<Order> listActiveOrders = getActiveOrders.handle();
        assertAll("Testing GetActiveOrders use case",
                () -> assertNotNull(listActiveOrders),
                () -> assertEquals(3, listActiveOrders.size()),
                () -> assertEquals(UUID.fromString("2a053bdb-c027-42ab-a0e4-0f2b67e68c7b"), listActiveOrders.get(0).getId()),
                () -> assertEquals(OrderStatus.ASSIGNED, listActiveOrders.get(0).getStatus()),
                () -> assertEquals(UUID.fromString("4931cb3f-80e3-40a2-880a-9d5e13cb6151"), listActiveOrders.get(1).getId()),
                () -> assertEquals(OrderStatus.CREATED, listActiveOrders.get(1).getStatus()),
                () -> assertEquals(UUID.fromString("d5de0c02-fabb-47fe-bb50-bfc7aa1c75d2"), listActiveOrders.get(2).getId()),
                () -> assertEquals(OrderStatus.ASSIGNED, listActiveOrders.get(2).getStatus())
        );
    }
}
