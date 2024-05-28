package ru.task.deliveryapp.core.domain.aggregate.courier.events;

import ru.task.deliveryapp.core.domain.aggregate.courier.CourierStatus;
import ru.task.deliveryapp.core.domain.event.DomainEvent;

import java.util.UUID;

/**
 * Не используется; задел на будушее
 */
public record CourierDomainEvent (UUID id, UUID courierId, CourierStatus courierStatus) implements DomainEvent  {
}
