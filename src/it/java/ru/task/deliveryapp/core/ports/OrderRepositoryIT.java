package ru.task.deliveryapp.core.ports;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.infrastructure.adapters.postgres.entity.OrderEntity;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Интеграционный тест репозитория OrderRepository.
 */
@SpringBootTest
@ActiveProfiles("test")
public class OrderRepositoryIT {

    @Autowired
    OrderRepository repository;

    @Test
    @Sql("/order_data.sql")
    public void testGetAllAssigned() {
        List<Order> orders = repository.getAllAssigned();
        assertAll("Testing getAllAssigned() method",
                () -> assertEquals(2, orders.size()),
                () -> assertEquals(OrderStatus.ASSIGNED, orders.get(0).getStatus()),
                () -> assertEquals(OrderStatus.ASSIGNED, orders.get(1).getStatus())
        );
    }

    @Test
    @Sql("/order_data.sql")
    public void testGetAllNotAssigned() {
        List<Order> orders = repository.getAllNotAssigned();
        assertAll("Testing getAllNotAssigned() method",
                () -> assertEquals(3, orders.size()),
                () -> assertNotEquals(OrderStatus.ASSIGNED, orders.get(0).getStatus()),
                () -> assertNotEquals(OrderStatus.ASSIGNED, orders.get(1).getStatus()),
                () -> assertNotEquals(OrderStatus.ASSIGNED, orders.get(2).getStatus())
        );
    }
}
