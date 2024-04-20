package ru.task.deliveryapp.core.domain.orderaggregate;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import ru.task.deliveryapp.core.domain.courieraggregate.Courier;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;
import ru.task.deliveryapp.exception.WrongStateException;

import java.util.UUID;

@Entity
@Table(name="delivery_order")   // order - ключевое слово, поэтому так
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private UUID courierId; // сознательно не привязываю пока Courier к Order, поскольку это тема следующих занятий
    private OrderStatus status;
    private Location location;
    @Embedded
    private Weight weight;

    private Order() {}

    private Order(UUID id, Location location, Weight weight) {
        this.id = id;
        this.location = location;
        this.weight = weight;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCourierId() {
        return courierId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Location getLocation() {
        return location;
    }

    public Weight getWeight() {
        return weight;
    }

    /**
     * Создание заказа.
     *
     * @param id        идентификатор
     * @param location  координаты заказчика
     * @param weight    вес заказа
     * @return          заказ
     */
    public static Order create(UUID id, Location location, Weight weight) {
        return new Order(id, location, weight);
    }

    /**
     * Назначение заказа на курьера
     *
     * @param courier курьер
     */
    public void assign(Courier courier) {
        status = OrderStatus.ASSIGNED;
        courierId = courier.getId();
    }

    /**
     * Завершение заказа (заказ выполнен).
     */
    public void complete() {
        if (status == OrderStatus.ASSIGNED) {
            status = OrderStatus.COMPLETED;
        }
        else {
            // Заказ не может быть завершён, поскольку не был назначен.
            throw new WrongStateException("An order must non be completed because it has not be assigned.");
        }
    }
}
