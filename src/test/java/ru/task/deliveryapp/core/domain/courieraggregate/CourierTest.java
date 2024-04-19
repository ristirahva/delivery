package ru.task.deliveryapp.core.domain.courieraggregate;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.exception.WrongStateException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourierTest {

    @Test
    public void testCreate() {
        Courier courier = Courier.create("Бободжан", Transport.fromName(Transport.BICYCLE.getName()));
        assertEquals("Бободжан", courier.getName());
        assertEquals(Transport.BICYCLE.getName(), courier.getTransport().getName());
        assertEquals(1, courier.getLocation().getX());
        assertEquals(1, courier.getLocation().getY());
        assertEquals(CourierStatus.NOT_AVAILABLE, courier.getStatus());
    }

    @Test
    public void testMove() {
        Location targetLocation = Location.create(6, 9);
        Courier courier1 = Courier.create("Курьер 1", Transport.fromName("Пешеход"));
        Courier courier2 = Courier.create("Курьер 2", Transport.fromName("Велосипед"));
        Courier courier3 = Courier.create("Курьер 3", Transport.fromName("Мотороллер"));
        Courier courier4 = Courier.create("Курьер 4", Transport.fromName("Автомобиль"));

        courier1.move(targetLocation);
        assertEquals(2, courier1.getLocation().getX());
        assertEquals(1, courier1.getLocation().getY());

        courier2.move(targetLocation);
        assertEquals(3, courier2.getLocation().getX());
        assertEquals(1, courier2.getLocation().getY());

        courier3.move(targetLocation);
        assertEquals(4, courier3.getLocation().getX());
        assertEquals(1, courier3.getLocation().getY());

        courier3.move(targetLocation);
        assertEquals(6, courier3.getLocation().getX());
        assertEquals(2, courier3.getLocation().getY());

        courier4.move(targetLocation);
        assertEquals(5, courier4.getLocation().getX());
        assertEquals(1, courier4.getLocation().getY());

        courier4.move(targetLocation);
        assertEquals(6, courier4.getLocation().getX());
        assertEquals(4, courier4.getLocation().getY());

        courier4.move(targetLocation);
        assertEquals(6, courier4.getLocation().getX());
        assertEquals(8, courier4.getLocation().getY());

        courier4.move(targetLocation);
        assertEquals(6, courier4.getLocation().getX());
        assertEquals(9, courier4.getLocation().getY());
        assertEquals(CourierStatus.READY, courier4.getStatus());
    }

    @Test
    public void testStartWork_positive() {
        Courier courier = Courier.create("Курьер", Transport.fromName("Автомобиль"));
        courier.startWork();
        assertEquals(CourierStatus.READY, courier.getStatus());
    }

    @Test
    public void testStartWork_negative() {
        Courier courier = Courier.create("Курьер", Transport.fromName("Автомобиль"));
        courier.startWork();
        courier.inWork();
        assertThrows(WrongStateException.class,
                ()-> {
                    courier.startWork();
                });
    }

    @Test
    public void testStopWork_positive() {
        Courier courier = Courier.create("Курьер", Transport.fromName("Велосипед"));
        courier.startWork();
        courier.stopWork();
        assertEquals(CourierStatus.NOT_AVAILABLE, courier.getStatus());
    }

    @Test
    public void testStopWork_negative() {
        Courier courier = Courier.create("Курьер", Transport.fromName("Велосипед"));
        courier.startWork();
        courier.inWork();
        assertThrows(WrongStateException.class,
                ()-> {
                    courier.stopWork();
                });
    }

    @Test
    public void testInWork_positive() {
        Courier courier = Courier.create("Курьер", Transport.fromName("Мотороллер"));
        courier.startWork();
        courier.inWork();
        assertEquals(CourierStatus.BUSY, courier.getStatus());
    }

    @Test
    public void testInWork_negative_busy() {
        Courier courier = Courier.create("Курьер", Transport.fromName("Мотороллер"));
        courier.startWork();
        courier.inWork();
        assertThrows(WrongStateException.class,
                ()-> {
                    courier.inWork();
                });
    }

    @Test
    public void testInWork_negative_na() {
        Courier courier = Courier.create("Курьер", Transport.fromName("Мотороллер"));
        assertThrows(WrongStateException.class,
                ()-> {
                    courier.inWork();
                });
    }

    @Test
    public void testCalculateTimeToPoint() {
        Location targetLocation = Location.create(6, 9);
        Courier courier1 = Courier.create("Курьер 1", Transport.fromName("Пешеход"));
        Courier courier2 = Courier.create("Курьер 2", Transport.fromName("Велосипед"));
        Courier courier3 = Courier.create("Курьер 3", Transport.fromName("Мотороллер"));
        Courier courier4 = Courier.create("Курьер 4", Transport.fromName("Автомобиль"));

        assertEquals(13, courier1.calculateTimeToPoint(targetLocation));
        assertEquals(7, courier2.calculateTimeToPoint(targetLocation));
        assertEquals(5, courier3.calculateTimeToPoint(targetLocation));
        assertEquals(4, courier4.calculateTimeToPoint(targetLocation));
    }
}
