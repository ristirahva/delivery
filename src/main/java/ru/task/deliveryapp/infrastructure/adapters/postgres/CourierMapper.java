package ru.task.deliveryapp.infrastructure.adapters.postgres;

import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.infrastructure.adapters.postgres.entity.CourierEntity;

public class CourierMapper {
    public static Courier toDomain(CourierEntity entity) {
        return Courier.create(entity.getId(), entity.getName(), entity.getTransport(), entity.getLocation(), entity.getStatus());
    }

    public static CourierEntity toEntity(Courier courier) {
        return new CourierEntity(courier.getId(), courier.getName(), courier.getTransport(), courier.getLocation(), courier.getStatus());
    }
}
