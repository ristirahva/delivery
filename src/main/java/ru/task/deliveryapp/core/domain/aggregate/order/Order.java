package ru.task.deliveryapp.core.domain.aggregate.order;

import ru.task.deliveryapp.core.domain.aggregate.Aggregate;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.order.events.OrderDomainEvent;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;
import ru.task.deliveryapp.exception.WrongStateException;

import java.util.UUID;

/**
 * Order aggregate
 */
public class Order extends Aggregate<OrderDomainEvent> {

    private UUID id;
    private UUID courierId;
    private OrderStatus status;
    private Location location;
    private Weight weight;

    private Order(UUID id, UUID courierId, OrderStatus status, Location location, Weight weight) {
        this.id = id;
        this.courierId = courierId;
        this.status = status;
        this.location = location;
        this.weight = weight;

        raiseDomainEvent(new OrderDomainEvent(UUID.randomUUID(), id, status));
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
        return new Order(id, null, OrderStatus.CREATED, location, weight);
    }

    /**
     * Создание заказа (маппинг из entity)
     *
     * @param id        идентификатор
     * @param status    статус
     * @param location  координаты заказчика
     * @param weight    вес заказа
     * @return          заказ
     */
    public static Order create(UUID id, UUID courierId, OrderStatus status, Location location, Weight weight) {
        return new Order(id, courierId, status, location, weight);
    }

    /**
     * Назначение заказа на курьера
     *
     * @param courier курьер
     */
    public void assign(Courier courier) {
        status = OrderStatus.ASSIGNED;
        courierId = courier.getId();
        raiseDomainEvent(new OrderDomainEvent(UUID.randomUUID(), id, status));
    }

    /**
     * Завершение заказа (заказ выполнен).
     */
    public void complete() {
        if (status == OrderStatus.ASSIGNED) {
            status = OrderStatus.COMPLETED;
            raiseDomainEvent(new OrderDomainEvent(UUID.randomUUID(), id, status));
        }
        else {
            // Заказ не может быть завершён, поскольку не был назначен.
            throw new WrongStateException("An order must non be completed because it has not be assigned.");
        }
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", courierId=" + courierId +
                ", status=" + status +
                ", location=" + location +
                ", weight=" + weight +
                '}';
    }
}
