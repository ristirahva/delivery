package ru.task.deliveryapp.core.ports;

import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;

import java.util.List;
import java.util.UUID;

public interface CourierRepository {
    Courier add(Courier courier);
    void update(Courier courier);
    Courier get(UUID courierId);
    List<Courier> getAllReady();
    List<Courier> getAllBusy();
}
