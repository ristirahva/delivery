package ru.task.deliveryapp.core.domain.sharedkernel;

import jakarta.persistence.Embeddable;
import ru.task.deliveryapp.exception.ValidationException;

import java.util.Objects;

/**
 * Координаты
 */
@Embeddable
public class Location  {
    public static int MIN_VALUE = 1;
    public static int MAX_VALUE = 10;

    private int x, y;

    private Location() {}

    private Location (int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Создание координаты
     *
     * @param x абсцисса
     * @param y ордината
     * @return координата
     */
    public static Location create(int x, int y) {
        if (x < MIN_VALUE || x > MAX_VALUE || y < MIN_VALUE || y > MAX_VALUE) {
            // Координаты должна принимать значения в пределах от %d до %d включительно
            throw new ValidationException(String.format("Coordinates must be between %d and %d inclusive", MIN_VALUE, MAX_VALUE));
        }
        Location location = new Location(x, y);
        return location;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * расстояние между координатами
     * @param target целевая координата
     * @return расстояние
     */
    public int distanceTo(Location target) {
        return Math.abs(target.getX() - x) + Math.abs(target.getY() - y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
