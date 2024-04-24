package ru.task.deliveryapp.core.ports;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.courier.CourierStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Интеграционный тест репозитория CourierRepository.
 */
@SpringBootTest
public class CourierRepositoryIT {
    @Autowired
    CourierRepository repository;

    @Test
    @Sql("courier_data.sql")
    public void testGetAllReady() {
        List<Courier> couriers = repository.getAllReady();
        assertAll("Testing getAllReady() method",
                () -> assertEquals(2, couriers.size()),
                () -> assertEquals(CourierStatus.READY, couriers.get(0).getStatus()),
                () -> assertEquals(CourierStatus.READY, couriers.get(1).getStatus())
        );
    }

    @Test
    @Sql("courier_data.sql")
    public void testGetAllBusy() {
        List<Courier> couriers = repository.getAllBusy();
        assertAll("Testing getAllBusy() method",
                () -> assertEquals(1, couriers.size()),
                () -> assertEquals(CourierStatus.BUSY, couriers.get(0).getStatus())
        );
    }
}
