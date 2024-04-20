package ru.task.deliveryapp.core.domain.courieraggregate;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.exception.WrongStateException;

import static org.junit.jupiter.api.Assertions.*;

public class CourierTest {

    @Test
    public void testCreate() {
        Courier courier = Courier.create("Курьер Бободжан", Transport.fromName(Transport.BICYCLE.getName()));
        assertAll("Testing create() method",
                () -> assertEquals("Курьер Бободжан", courier.getName()),
                () -> assertEquals(Transport.BICYCLE, courier.getTransport()),
                () -> assertEquals(1, courier.getLocation().getX()),
                () -> assertEquals(1, courier.getLocation().getY()),
                () -> assertEquals(CourierStatus.NOT_AVAILABLE, courier.getStatus())
        );
    }

    @Test
    public void testMove_pedestrian() {
        Location targetLocation = Location.create(6, 9);
        Courier courier = Courier.create("Пеший курьер", Transport.fromName("Пешеход"));
        courier.move(targetLocation);
        assertAll("Testing testMove() method with pedestrian way of moving",
                () -> assertEquals(2, courier.getLocation().getX()),
                () -> assertEquals(1, courier.getLocation().getY())
        );
    }

    @Test
    public void testMove_bicycle() {
        Location targetLocation = Location.create(6, 9);
        Courier courier = Courier.create("Курьер на велосипеде", Transport.fromName("Велосипед"));
        courier.move(targetLocation);
        assertAll("Testing testMove() method with bicycle",
                () -> assertEquals(3, courier.getLocation().getX()),
                () -> assertEquals(1, courier.getLocation().getY())
        );
    }

    @Test
    public void testMove_scooter() {
        Location targetLocation = Location.create(6, 9);
        Courier courier = Courier.create("Курьер на мотороллере", Transport.fromName("Мотороллер"));
        courier.move(targetLocation);
        assertAll("Testing testMove() method with scooter, step 1",
                () -> assertEquals(4, courier.getLocation().getX()),
                () -> assertEquals(1, courier.getLocation().getY())
        );
        courier.move(targetLocation);
        assertAll("Testing testMove() method with scooter, step 2",
                () -> assertEquals(6, courier.getLocation().getX()),
                () -> assertEquals(2, courier.getLocation().getY())
        );
    }

    @Test
    public void testMove_car() {
        Location targetLocation = Location.create(6, 9);
        Courier courier = Courier.create("Курьер на автомобиле", Transport.fromName("Автомобиль"));

        courier.move(targetLocation);
        assertAll("Testing testMove() method with car, step 1",
                () -> assertEquals(5, courier.getLocation().getX()),
                () -> assertEquals(1, courier.getLocation().getY())
        );

        courier.move(targetLocation);
        assertAll("Testing testMove() method with car, step 2",
                () -> assertEquals(6, courier.getLocation().getX()),
                () -> assertEquals(4, courier.getLocation().getY())
        );

        courier.move(targetLocation);
        assertAll("Testing testMove() method with car, step 3",
                () -> assertEquals(6, courier.getLocation().getX()),
                () -> assertEquals(8, courier.getLocation().getY())
        );

        courier.move(targetLocation);
        assertAll("Testing testMove() method with car, step 4, the final one",
                () -> assertEquals(6, courier.getLocation().getX()),
                () -> assertEquals(9, courier.getLocation().getY()),
                () -> assertEquals(CourierStatus.READY, courier.getStatus())
        );
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
        Courier courier1 = Courier.create("Курьер-пешеход", Transport.fromName("Пешеход"));
        Courier courier2 = Courier.create("Курьер-велосипедист", Transport.fromName("Велосипед"));
        Courier courier3 = Courier.create("Курьер-мотороллерщик", Transport.fromName("Мотороллер"));
        Courier courier4 = Courier.create("Курьер-автоводитель", Transport.fromName("Автомобиль"));

        assertAll("Testing calculateTimeToPoint() method",
                () -> assertEquals(13, courier1.calculateTimeToPoint(targetLocation)),
                () -> assertEquals(7,  courier2.calculateTimeToPoint(targetLocation)),
                () -> assertEquals(5,  courier3.calculateTimeToPoint(targetLocation)),
                () -> assertEquals(4,  courier4.calculateTimeToPoint(targetLocation))
        );
    }
}
