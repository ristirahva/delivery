package ru.task.deliveryapp.core.domain.sharedkernel;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.exception.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

public final class LocationTest {
    @Test
    public void testCreate_positive() {
        Location location = Location.create(2, 5);
        assertNotNull(location);
        assertEquals(2, location.getX());
        assertEquals(5, location.getY());
    }

    @Test
    public void testCreate_negative1() {
        assertThrows(ValidationException.class,
                ()-> {
                    Location.create(-2, 0);
                });
    }

    @Test
    public void testCreate_negative2() {
        assertThrows(ValidationException.class,
                ()-> {
                    Location.create(11, 10);
                });
    }

    public void testDistanceTo_different() {
        assertEquals(3, Location.create(2, 7).distanceTo(Location.create(1, 9)));
    }

    public void testDistanceTo_equals() {
        assertEquals(0, Location.create(4,6).distanceTo(Location.create(4, 6)));
    }
}
