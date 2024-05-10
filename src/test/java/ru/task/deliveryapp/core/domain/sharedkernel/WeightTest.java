package ru.task.deliveryapp.core.domain.sharedkernel;

import org.junit.jupiter.api.Test;
import ru.task.deliveryapp.exception.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

public class WeightTest {
    @Test
    public void testCreate_positive() {
        Weight weight = Weight.create(29);
        assertAll("Testing create() method positive case",
                () -> assertNotNull(weight),
                () -> assertEquals(29, weight.getWeightValue())
        );
    }

    @Test
    public void testCreate_negative() {
        assertThrows(ValidationException.class, ()-> {
            Weight.create(-12);
        });
    }

    @Test
    public void testCompareTo_equals() {
        assertEquals(0, Weight.create(5).compareTo(Weight.create(5)));
    }

    @Test
    public void testCompareTo_less() {
        assertEquals(-1, Weight.create(5).compareTo(Weight.create(25)));
    }

    @Test
    public void testCompareTo_more() {
        assertEquals(1, Weight.create(45).compareTo(Weight.create(25)));
    }
}
