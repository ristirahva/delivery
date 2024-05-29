package ru.task.deliveryapp.core.domain.aggregate.courier;

import io.micrometer.common.util.StringUtils;
import ru.task.deliveryapp.core.domain.aggregate.Aggregate;
import ru.task.deliveryapp.core.domain.aggregate.courier.events.CourierDomainEvent;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.exception.ValidationException;
import ru.task.deliveryapp.exception.WrongStateException;

import java.util.UUID;

/*
Courier aggregate
 */
public class Courier extends Aggregate<CourierDomainEvent> {
    private final static Location INITIAL_LOCATION = Location.create(1, 1);
    private final static int NEGATIVE_DIRECTION = -1;
    private final static int POSITIVE_DIRECTION = 1;
    private final static int NO_DIRECTION = 0;

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
        int xDistance = targetLocation.getX() - location.getX();
        int yDistance = targetLocation.getY() - location.getY();
        int xDirection = xDistance > 0 ? POSITIVE_DIRECTION : (xDistance < 0 ? NEGATIVE_DIRECTION : NO_DIRECTION);
        int yDirection = yDistance > 0 ? POSITIVE_DIRECTION : (yDistance < 0 ? NEGATIVE_DIRECTION : NO_DIRECTION);;
        if (transport.getSpeed() >= location.distanceTo(targetLocation)) {
            location = targetLocation;
        }
        else if (transport.getSpeed() <= Math.abs(xDistance)) {
            location = Location.create(location.getX() + transport.getSpeed() * xDirection, location.getY());
        }
        else {
            location = Location.create(targetLocation.getX(), location.getY() + transport.getSpeed() * yDirection - Math.abs(xDistance));
        }
    }

    /**
     * Начало работы курьера.
     */
    public void startWork() {
        if (status == CourierStatus.BUSY) {
            throw new WrongStateException("A courier is busy so cannot start working.");
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
            throw new WrongStateException("A courier is busy so cannot stop working.");
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

    /**
     * Завершение заказа.
     */
    public void completeOrder() {
        if (status == CourierStatus.BUSY) {
            status = CourierStatus.READY;
        }
        else {
            throw new WrongStateException(String.format("It is possible to complete the order only if courier status is busy"));
        }
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

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", transport=" + transport +
                ", location=" + location +
                ", status=" + status +
                '}';
    }
}
