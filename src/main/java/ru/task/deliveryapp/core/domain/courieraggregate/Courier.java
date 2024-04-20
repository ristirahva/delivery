package ru.task.deliveryapp.core.domain.courieraggregate;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.exception.WrongStateException;

import java.util.UUID;

@Entity
public class Courier {

    private final static Location INITIAL_LOCATION = Location.create(1, 1);

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    //@Type(value = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String name;
    private Transport transport;
    @Embedded
    private Location location;
    private CourierStatus status;

    private Courier() {}

    private Courier(String name, Transport transport) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.transport = transport;
        this.location = INITIAL_LOCATION;
        this.status = CourierStatus.NOT_AVAILABLE;
    }

    /**
     * Создание курьера.
     *
     * @param name      имя курьера
     * @param transport транспорт курьера
     * @return          курьер
     */
    public static Courier create(String name, Transport transport) {
        return new Courier(name, transport);
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
