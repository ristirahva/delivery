package ru.task.deliveryapp.core.domain.sharedkernel;

import jakarta.persistence.Embeddable;
import ru.task.deliveryapp.exception.ValidationException;

@Embeddable
public class Weight implements Comparable<Weight>{

    private int weightValue;

    private Weight() {}
    private Weight(String input){
        try {
            weightValue = Integer.valueOf(input);
        }
        catch(NumberFormatException nfe) {
            // Неправильный формат
            throw new ValidationException(String.format("Wring format: '%s'", input));
        }
        if (weightValue <= 0) {
            // Недопустимое значение
            throw new ValidationException("Illegal value: " + weightValue);
        }
    }

    public static Weight create(String input) {
        return new Weight(input);
    }

    @Override
    public int compareTo(Weight target) {
        return getWeightValue() == target.getWeightValue() ? 0 : (getWeightValue() < target.getWeightValue() ? -1 : 1);
    }

    public int getWeightValue() {
        return weightValue;
    }
}
