package ru.task.deliveryapp.core.domain.orderaggregate;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.core.domain.courieraggregate.Courier;
import ru.task.deliveryapp.core.domain.courieraggregate.Transport;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;
import ru.task.deliveryapp.exception.WrongStateException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    public void testCreate() {
        UUID id = UUID.randomUUID();
        Order order = Order.create(id, Location.create(2, 3), Weight.create("7"));
        assertAll("Testing create() method",
                () -> assertEquals(id, order.getId()),
                () -> assertEquals(2, order.getLocation().getX()),
                () -> assertEquals(3, order.getLocation().getY()),
                () -> assertEquals(7, order.getWeight().getWeightValue())
        );
    }

    @Test
    public void testAssign() {
        Order order = Order.create(UUID.randomUUID(), Location.create(2, 3), Weight.create("9"));
        order.assign(Courier.create("Усмон", Transport.PEDESTRIAN));
        assertEquals(OrderStatus.ASSIGNED, order.getStatus());
    }

    @Test
    public void testComplete_positive() {
        Order order = Order.create(UUID.randomUUID(), Location.create(2, 3), Weight.create("9"));
        order.assign(Courier.create("Усмон", Transport.PEDESTRIAN));
        order.complete();
        assertEquals(OrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    public void testComplete_negative() {
        Order order = Order.create(UUID.randomUUID(), Location.create(2, 3), Weight.create("9"));
        assertThrows(WrongStateException.class,
                ()-> {
                    order.complete();
                });
    }
}
