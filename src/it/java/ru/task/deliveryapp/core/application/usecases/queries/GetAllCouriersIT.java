package ru.task.deliveryapp.core.application.usecases.queries;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.usecases.queries.GetAllCouriers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GetAllCouriersIT {
    @Autowired
    GetAllCouriers getAllCouriers;

    @Test
    @Sql("/courier_data.sql")
    public void testHandle() {
        List<Courier> listAllCouriers = getAllCouriers.handle();
        assertAll("Testing GetAllCouriers use case",
                () -> assertNotNull(listAllCouriers),
                () -> assertEquals(4, listAllCouriers.size()),
                () -> assertEquals("Courier 1", listAllCouriers.get(0).getName()),
                () -> assertEquals("Courier 2", listAllCouriers.get(1).getName()),
                () -> assertEquals("Courier 3", listAllCouriers.get(2).getName()),
                () -> assertEquals("Courier 4", listAllCouriers.get(3).getName())
        );
    }
}
