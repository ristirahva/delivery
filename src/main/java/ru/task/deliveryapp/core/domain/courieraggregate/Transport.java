package ru.task.deliveryapp.core.domain.courieraggregate;

import ru.task.deliveryapp.core.domain.sharedkernel.Weight;
import ru.task.deliveryapp.exception.ObjectNotFoundException;

import java.util.stream.Stream;

public enum Transport {
    PEDESTRIAN(1, "Пешеход", 1, Weight.create("1")),
    BICYCLE(3, "Велосипед", 2, Weight.create("4")),
    SCOOTER(2, "Мотороллер", 3, Weight.create("6")),
    CAR(4, "Автомобиль", 4, Weight.create("8"));

    private final int id;
    private final String name;
    private final int speed;
    private final Weight capacity;

    Transport(int id, String name, int speed, Weight capacity) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Weight getCapacity() {
        return capacity;
    }

    /**
     * Получение вида транспорта по идентификатору.
     *
     * @param id    идентификатор
     * @return      вид транспорта
     */
    public static Transport fromId(int id) {
        return Stream.of(values())
                .filter(transport -> transport.getId() == id)
                .findAny()
                .orElseThrow(
                        () -> {
                            return new ObjectNotFoundException(String.format("Транспорт с id = %d не найден", id));
                        }
                );
    }

    /**
     * Получение вида транспорта по его названию.
     *
     * @param name  название транспорта
     * @return      вид транспорта
     */
    public static Transport fromName(String name) {
        return Stream.of(values())
                .filter(transport -> transport.getName().equals(name))
                .findAny()
                .orElseThrow( () -> {
                    return new ObjectNotFoundException(String.format("Транспорт с названием '%s' не найден", name));
                });
    }
}
