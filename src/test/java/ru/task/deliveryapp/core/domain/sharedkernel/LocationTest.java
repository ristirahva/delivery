package ru.task.deliveryapp.core.domain.sharedkernel;

import org.junit.jupiter.api.BeforeEach;
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
    public void testCreate_negative() {
        assertThrows(ValidationException.class,
                ()-> {
                    Location.create(-2, 0);
                });
    }
}
