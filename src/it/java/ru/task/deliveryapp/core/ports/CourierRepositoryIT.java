package ru.task.deliveryapp.core.ports;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.courier.CourierStatus;
import ru.task.deliveryapp.core.ports.db.CourierRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Интеграционный тест репозитория CourierRepository.
 */
@SpringBootTest
@ActiveProfiles("test")
public class CourierRepositoryIT {
    @Autowired
    private CourierRepository repository;

    @Test
    @Sql("/courier_data.sql")
    public void testGet() {
        String courierId = "bf79a004-56d7-4e5f-a21c-0a9e5e08d10d";
        Courier courier = repository.get(UUID.fromString(courierId));
        assertAll("Testing get() method",
                () -> assertNotNull(courier),
                () -> assertEquals(courierId, courier.getId().toString()),
                () -> assertEquals("Courier 1", courier.getName()),
                () -> assertEquals(CourierStatus.NOT_AVAILABLE, courier.getStatus())
                );
    }

    @Test
    @Sql("/courier_data.sql")
    public void testGetAllReady() {
        List<Courier> couriers = repository.getAllReady();
        assertAll("Testing getAllReady() method",
                () -> assertEquals(2, couriers.size()),
                () -> assertEquals(CourierStatus.READY, couriers.get(0).getStatus()),
                () -> assertEquals(CourierStatus.READY, couriers.get(1).getStatus())
        );
    }

    @Test
    @Sql("/courier_data.sql")
    public void testGetAllBusy() {
        List<Courier> couriers = repository.getAllBusy();
        assertAll("Testing getAllBusy() method",
                () -> assertEquals(1, couriers.size()),
                () -> assertEquals(CourierStatus.BUSY, couriers.get(0).getStatus())
        );
    }
}
