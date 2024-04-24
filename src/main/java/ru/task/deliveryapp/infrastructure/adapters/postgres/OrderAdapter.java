package ru.task.deliveryapp.infrastructure.adapters.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task.deliveryapp.core.domain.aggregate.courier.CourierStatus;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.ports.OrderRepository;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.exception.DbException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderAdapter implements OrderRepository {

    @Autowired
    ru.task.deliveryapp.infrastructure.adapters.postgres.repository.OrderRepository repository;

    @Override
    public Order add(Order order) {
        if ((order.getId() == null) || repository.findById(order.getId()).isEmpty()) {
            return OrderMapper.toDomain(repository.save(OrderMapper.toEntity(order)));
        }
        else {
            throw new DbException("A new courier cannot be added because it exists already");
        }
    }

    @Override
    public void update(Order order) {
        if (order.getId() != null && !repository.findById(order.getId()).isEmpty()) {
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
        return repository.findByStatus(OrderStatus.ASSIGNED).stream().map(entity -> OrderMapper.toDomain(entity)).collect(Collectors.toList());
    }

    @Override
    public List<Order> getAllNotAssigned() {
        return repository.findByStatusNot(OrderStatus.ASSIGNED).stream().map(entity -> OrderMapper.toDomain(entity)).collect(Collectors.toList());
    }
}