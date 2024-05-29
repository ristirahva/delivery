package ru.task.deliveryapp.infrastructure.adapters.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.ports.db.OrderRepository;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.core.ports.kafka.KafkaRepository;
import ru.task.deliveryapp.exception.DbException;
import ru.task.deliveryapp.infrastructure.adapters.kafka.KafkaOrderAdapter;
import ru.task.deliveryapp.infrastructure.adapters.postgres.repository.OrderJpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * DB operations with orders.
 */
@Component
public class DbOrderAdapter implements OrderRepository {

    private static final Logger log = LoggerFactory.getLogger(DbOrderAdapter.class);

    private final OrderJpaRepository repository;
    private KafkaRepository kafkaOrderAdapter;

    @Autowired
    public DbOrderAdapter(OrderJpaRepository repository, KafkaRepository kafkaRepository) {
        this.repository = repository;
        this.kafkaOrderAdapter = kafkaRepository;
    }

    @Override
    public Order add(Order order) {
        log.info("Add order into DB: " + order);
        if ((order.getId() == null) || repository.findById(order.getId()).isEmpty()) {
            kafkaOrderAdapter.sendEvents(order);
            return OrderMapper.toDomain(repository.save(OrderMapper.toEntity(order)));
        }
        else {
            throw new DbException("A new courier cannot be added because it exists already");
        }
    }

    @Override
    public void update(Order order) {
        if (order.getId() != null && !repository.findById(order.getId()).isEmpty()) {
            kafkaOrderAdapter.sendEvents(order);
            repository.save(OrderMapper.toEntity(order));
        }
        else {
            throw new DbException("A courier cannot be updates because it doesn't exist");
        }
    }

    @Override
    public Order get(UUID orderId) {
        return OrderMapper.toDomain(repository.findById(orderId).get());
    }

    @Override
    public List<Order> getAllAssigned() {
        return OrderMapper.listToDomain(repository.findByStatus(OrderStatus.ASSIGNED));
    }

    @Override
    public List<Order> getAllNotAssigned() {
        return OrderMapper.listToDomain(repository.findByStatusNot(OrderStatus.ASSIGNED));
    }
}
