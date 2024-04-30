package ru.task.deliveryapp.core.application.usecases.commands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.courier.CourierStatus;
import ru.task.deliveryapp.core.ports.CourierRepository;
import ru.task.deliveryapp.exception.ValidationException;
import ru.task.deliveryapp.exception.WrongStateException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StartWorkHandlerIT {

    @Autowired
    private StartWorkHandler handler;

    @Autowired
    private CourierRepository repository;

    @Test
    @Sql("/courier_data.sql")
    public void testHandle_positive() {
        UUID courierId = UUID.fromString("bf79a004-56d7-4e5f-a21c-0a9e5e08d10d");
        StartWorkCommand command = new StartWorkCommand(courierId);
        handler.handle(command);
        Courier courier = repository.get(courierId);
        assertAll("Testing StartWork use case",
                () -> assertNotNull(courier),
                () -> assertEquals(courierId, courier.getId()),
                () -> assertEquals(CourierStatus.READY, courier.getStatus())
        );
    }

    @Test
    @Sql("/courier_data.sql")
    public void testHandle_negative_id_is_null() {
        UUID courierId = null;
        var command = new StartWorkCommand(courierId);
        assertThrows(InvalidDataAccessApiUsageException.class,
                ()-> {
                    handler.handle(command);
                }
        );
    }

    @Test
    @Sql("/courier_data.sql")
    public void testHandle_negative_wrong_state() {
        UUID courierId = UUID.fromString("407f68be-5adf-4e72-81bc-b1d8e9574cf8");
        var command = new StartWorkCommand(courierId);
        assertThrows(WrongStateException.class,
                ()-> {
                    handler.handle(command);
                }
        );
    }
}
