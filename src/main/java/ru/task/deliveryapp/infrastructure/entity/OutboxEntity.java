package ru.task.deliveryapp.infrastructure.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.core.domain.aggregate.order.events.OrderDomainEvent;

import java.util.UUID;

@Entity
@Table(name="outbox")
public class OutboxEntity {
    @Id
    private UUID id;

    @Column(name = "event_type", nullable = false)
    private OrderStatus eventType;

    //@JdbcTypeCode(SqlTypes.JSON)
    //@JdbcTypeCode(SqlTypes.VARCHAR)
    @Convert(converter = DomainEventConverter.class)
    @Column(name ="event_payload", nullable = false)
    private OrderDomainEvent eventPayload;

    private OutboxEntity() {}

    public OutboxEntity(UUID id, OrderStatus eventType, OrderDomainEvent eventPayload) {
        this.id = id;
        this.eventType = eventType;
        this.eventPayload = eventPayload;
    }

    public UUID getId() {
        return id;
    }

    public OrderStatus getEventType() {
        return eventType;
    }

    public OrderDomainEvent getEventPayload() {
        return eventPayload;
    }

    @Override
    public String toString() {
        return "OutboxEntity{" +
                "id=" + id +
                ", eventType=" + eventType +
                ", eventPayload='" + eventPayload + '\'' +
                '}';
    }
}
