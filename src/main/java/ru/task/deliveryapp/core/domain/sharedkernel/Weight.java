package ru.task.deliveryapp.core.domain.sharedkernel;

import ru.task.deliveryapp.exception.ValidationException;

public final class Weight implements Comparable<Weight>{

    private int value;

    private Weight() {}

    private Weight(String input){
        try {
            value = Integer.valueOf(input);
        }
        catch(NumberFormatException nfe) {
            throw new ValidationException("Неправильный формат");
        }
        if (value <= 0) {
            throw new ValidationException("Недопустимое значение: " + value);
        }
    }

    public static Weight create(String input) {
        return new Weight(input);
    }

    @Override
    public int compareTo(Weight target) {
        return getValue() == target.getValue() ? 0 : (getValue() < target.getValue() ? -1 : 1);
    }

    public int getValue() {
        return value;
    }
}
