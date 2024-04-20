package ru.task.deliveryapp.core.ports;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.domain.orderaggregate.Order;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Интеграционный тест репозитория OrderRepository.
 */
@DataJpaTest
public class OrderRepositoryIT {

    @Autowired
    OrderRepository repository;

    @Test
    @Sql("order_data.sql")
    public void testGetAllAssigned() {
        Collection<Order> orders = repository.getAllAssigned();
        assertEquals(2, orders.size());
    }

    @Test
    @Sql("order_data.sql")
    public void testGetAllNotAssigned() {
        Collection<Order> orders = repository.getAllNotAssigned();
        assertEquals(3, orders.size());
    }
}
