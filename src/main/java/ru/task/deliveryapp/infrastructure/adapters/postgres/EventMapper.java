package ru.task.deliveryapp.infrastructure.adapters.postgres;

import ru.task.deliveryapp.core.domain.aggregate.order.events.OrderDomainEvent;
import ru.task.deliveryapp.infrastructure.entity.OutboxEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class EventMapper {
    public static OutboxEntity toEntity(OrderDomainEvent domainEvent) {
        OutboxEntity outbox = new OutboxEntity(UUID.randomUUID(), domainEvent.orderStatus(), domainEvent);
        return outbox;
    }

    public static List<OutboxEntity> ToEntities(List<OrderDomainEvent> domainEventList) {
        return domainEventList.stream().map(event -> toEntity(event)).collect(Collectors.toList());
    }
}
