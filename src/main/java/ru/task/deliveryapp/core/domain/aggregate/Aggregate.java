package ru.task.deliveryapp.core.domain.aggregate;

import ru.task.deliveryapp.core.domain.aggregate.order.events.OrderDomainEvent;
import ru.task.deliveryapp.core.domain.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Работа с доменными событиями
 *
 * @param <T>
 */
public abstract class Aggregate <T extends DomainEvent> {
    private List<T> domainEvents = new ArrayList<>();

    public void raiseDomainEvent(T domainEvent) {
        domainEvents.add(domainEvent);
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }

    public List<T> getDomainEvents() {
        return domainEvents;
    }
}
