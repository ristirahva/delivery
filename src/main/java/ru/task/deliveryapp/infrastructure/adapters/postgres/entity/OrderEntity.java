package ru.task.deliveryapp.infrastructure.adapters.postgres.entity;

import jakarta.persistence.*;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;
import ru.task.deliveryapp.exception.WrongStateException;

import java.util.UUID;

@Entity
@Table(name="delivery_order")   // order - ключевое слово, поэтому так
public class OrderEntity {
    @Id
    private UUID id;
    private UUID courierId; // сознательно не привязываю Courier к Order
    private OrderStatus status;
    private Location location;
    @Embedded
    private Weight weight;

    private OrderEntity() {
    }

    public OrderEntity(UUID id, UUID courierId, OrderStatus status, Location location, Weight weight) {
        this.id = id;
        this.courierId = courierId;
        this.status = status;
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

}
