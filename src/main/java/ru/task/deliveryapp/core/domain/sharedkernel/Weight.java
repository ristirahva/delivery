package ru.task.deliveryapp.core.domain.sharedkernel;

import jakarta.persistence.Embeddable;
import ru.task.deliveryapp.exception.ValidationException;

import java.util.Objects;

@Embeddable
public class Weight implements Comparable<Weight>{

    private int weightValue;

    private Weight() {}
    private Weight(int input){
        weightValue = input;
        if (weightValue <= 0) {
            throw new ValidationException(String.format("Illegal value: %d, must be positive", weightValue));
        }
    }

    public static Weight create(int input) {
        return new Weight(input);
    }

    @Override
    public int compareTo(Weight target) {
        return getWeightValue() == target.getWeightValue() ? 0 : (getWeightValue() < target.getWeightValue() ? -1 : 1);
    }

    public int getWeightValue() {
        return weightValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight = (Weight) o;
        return weightValue == weight.weightValue;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(weightValue);
    }
}
