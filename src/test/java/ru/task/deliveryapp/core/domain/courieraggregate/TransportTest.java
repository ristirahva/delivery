package ru.task.deliveryapp.core.domain.courieraggregate;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.exception.ObjectNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class TransportTest {

    @Test
    public void testFromId_positive() {
        assertNotNull(Transport.fromId(3));
        assertEquals("Велосипед", Transport.fromId(3).getName());
    }

    @Test
    public void testFromId_negative() {
        assertThrows(ObjectNotFoundException.class,
                ()-> {
                    Transport.fromId(33);
                });
    }

    @Test
    public void testFromName_positive() {
        assertNotNull(Transport.fromName("Автомобиль"));
        assertEquals(4, Transport.fromName("Автомобиль").getId());
    }

    @Test
    public void testFromName_negative() {
        assertThrows(ObjectNotFoundException.class,
                ()-> {
                    Transport.fromName("Самолёт");
                });
    }
}
