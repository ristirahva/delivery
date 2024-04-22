package ru.task.deliveryapp.infrastructure.adapters.postgres.entity;

import jakarta.persistence.*;
import ru.task.deliveryapp.core.domain.aggregate.courier.CourierStatus;
import ru.task.deliveryapp.core.domain.aggregate.courier.Transport;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;
import ru.task.deliveryapp.exception.WrongStateException;

import java.util.UUID;

@Entity(name="courier")
public class CourierEntity {

    private final static Location INITIAL_LOCATION = Location.create(1, 1);

    @Id
    private UUID id;
    private String name;
    private Transport transport;
    @Embedded
    private Location location;
    private CourierStatus status;

    private CourierEntity() {}

    public CourierEntity(UUID id, String name, Transport transport, Location location, CourierStatus status) {
        this.id = id;
        this.name = name;
        this.transport = transport;
        this.location = location;
        this.status = status;
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
