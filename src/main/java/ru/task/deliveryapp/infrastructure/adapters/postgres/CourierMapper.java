package ru.task.deliveryapp.infrastructure.adapters.postgres;

import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.infrastructure.adapters.postgres.entity.CourierEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CourierMapper {
    public static Courier toDomain(CourierEntity entity) {
        return Courier.create(entity.getId(), entity.getName(), entity.getTransport(), entity.getLocation(), entity.getStatus());
    }

    public static List<Courier> listToDomain(List<CourierEntity> courierEntityList) {
        return courierEntityList.stream().map(entity -> CourierMapper.toDomain(entity)).collect(Collectors.toList());
    }

    public static CourierEntity toEntity(Courier courier) {
        return new CourierEntity(courier.getId(), courier.getName(), courier.getTransport(), courier.getLocation(), courier.getStatus());
    }
}
