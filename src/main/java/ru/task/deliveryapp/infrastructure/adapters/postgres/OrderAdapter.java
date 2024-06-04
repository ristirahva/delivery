package ru.task.deliveryapp.infrastructure.adapters.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.aggregate.order.events.OrderDomainEvent;
import ru.task.deliveryapp.core.ports.db.OrderRepository;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.core.ports.msg.OrderProducer;
import ru.task.deliveryapp.exception.DbException;
import ru.task.deliveryapp.infrastructure.adapters.postgres.repository.OrderJpaRepository;
import ru.task.deliveryapp.infrastructure.adapters.postgres.repository.OutboxJpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * DB operations with orders.
 */
@Component
public class OrderAdapter implements OrderRepository {

    private static final Logger log = LoggerFactory.getLogger(OrderAdapter.class);

    private final OrderJpaRepository orderRepository;
    private final OutboxJpaRepository outboxRepository;

    @Autowired
    public OrderAdapter(OrderJpaRepository repository, OutboxJpaRepository outboxRepository) {
        this.orderRepository = repository;
        this.outboxRepository = outboxRepository;
    }

    @Override
    public Order add(Order order) {
        log.info("Add order into DB: " + order);
        if ((order.getId() == null) || orderRepository.findById(order.getId()).isEmpty()) {
            saveEventsIntoDB(order.getDomainEvents());
            return OrderMapper.toDomain(orderRepository.save(OrderMapper.toEntity(order)));
        }
        else {
            throw new DbException("A new order cannot be added because it exists already");
        }
    }

    @Override
    public void update(Order order) {
        if (order.getId() != null && !orderRepository.findById(order.getId()).isEmpty()) {
            saveEventsIntoDB(order.getDomainEvents());
            orderRepository.save(OrderMapper.toEntity(order));
        }
        else {
            throw new DbException("A courier cannot be updates because it doesn't exist");
        }
    }

    @Override
    public Order get(UUID orderId) {
        return OrderMapper.toDomain(orderRepository.findById(orderId).get());
    }

    @Override
    public List<Order> getAllAssigned() {
        return OrderMapper.listToDomain(orderRepository.findByStatus(OrderStatus.ASSIGNED));
    }

    @Override
    public List<Order> getAllNotAssigned() {
        return OrderMapper.listToDomain(orderRepository.findByStatusNot(OrderStatus.ASSIGNED));
    }

    private void saveEventsIntoDB(List<OrderDomainEvent> domainEventList) {
        outboxRepository.saveAll(EventMapper.ToEntities(domainEventList));
    }
}
