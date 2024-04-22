package ru.task.deliveryapp.core.domain.aggregate.courier;

import io.micrometer.common.util.StringUtils;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.exception.ValidationException;
import ru.task.deliveryapp.exception.WrongStateException;
import ru.task.deliveryapp.infrastructure.adapters.postgres.entity.CourierEntity;

import java.util.UUID;

public class Courier {
    private final static Location INITIAL_LOCATION = Location.create(1, 1);

    private UUID id;
    private String name;
    private Transport transport;
    private Location location;
    private CourierStatus status;

    private Courier(UUID id, String name, Transport transport, Location location, CourierStatus status) {
        this.id = id;
        this.name = name;
        this.transport = transport;
        this.location = location;
        this.status = status;
    }

    /**
     * Создание курьера.
     *
     * @param name      имя курьера
     * @param transport транспорт курьера
     * @return          курьер
     */
    public static Courier create(String name, Transport transport) {
        if (StringUtils.isBlank(name)) {
            throw new ValidationException("Courier name must not be blank");
        }
        return new Courier(UUID.randomUUID(), name, transport, INITIAL_LOCATION, CourierStatus.NOT_AVAILABLE);
    }

    /**
     * Создание курьера со всеми параметрами (для маппинга из БД)
     *
     * @param name      имя курьера
     * @param transport транспорт курьера
     * @return          курьер
     */
    public static Courier create(UUID id, String name, Transport transport, Location location, CourierStatus status) {
        return new Courier(id, name, transport, location, status);
    }

    /**
     * Продвижение курьера на один шаг в направлении заказчика.
     *
     * @param targetLocation координаты заказчика.
     */
    public void move(Location targetLocation) {
        if (transport.getSpeed() >= location.distanceTo(targetLocation)) {
            location = targetLocation;
            status = CourierStatus.READY;
        }
        else if (transport.getSpeed() <= (targetLocation.getX() - location.getX())) {
            location = Location.create(location.getX() + transport.getSpeed(), location.getY());
        }
        else {
            location = Location.create(targetLocation.getX(), location.getY() + transport.getSpeed() - (targetLocation.getX() - location.getX()));
        }
    }

    /**
     * Начало работы курьера.
     */
    public void startWork() {
        if (status == CourierStatus.BUSY) {
            // Курьер не может начать работу, поскольку уже занят.
            throw new WrongStateException("A courier must not start to work because he/she is already busy.");
        }
        else {
            status = CourierStatus.READY;
        }
    }

    /**
     * Окончание работы курьера.
     */
    public void stopWork() {
        if (status == CourierStatus.BUSY) {
            // Курьер не может закончить работу, поскольку занят.
            throw new WrongStateException("A courier must not stop to work because he/she is busy.");
        }
        else {
            status = CourierStatus.NOT_AVAILABLE;
        }
    }

    /**
     * Назначение заказа на курьера.
     */
    public void inWork() {
        if (status == CourierStatus.BUSY) {
            // Курьер уже занят.
            throw new WrongStateException("A courier is already busy.");
        }
        if (status == CourierStatus.NOT_AVAILABLE) {
            // Курьер недоступен.
            throw new WrongStateException("A courier is not available.");
        }
        status = CourierStatus.BUSY;
    }

    /**
     * Подсчёт расстояния до заказчика.
     *
     * @param targetLocation    координаты заказчика
     * @return                  расстояние до заказчика
     */
    public int calculateTimeToPoint(Location targetLocation) {
        int steps = location.distanceTo(targetLocation);
        int timeToPoint = steps / transport.getSpeed() + ((steps % transport.getSpeed()) == 0 ? 0 : 1);
        return timeToPoint;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Transport getTransport() {
        return transport;
    }

    public Location getLocation() {
        return location;
    }

    public CourierStatus getStatus() {
        return status;
    }

}
