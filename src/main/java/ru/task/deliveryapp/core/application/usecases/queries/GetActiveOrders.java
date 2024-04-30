package ru.task.deliveryapp.core.application.usecases.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;
import ru.task.deliveryapp.infrastructure.adapters.postgres.OrderMapper;
import ru.task.deliveryapp.infrastructure.adapters.postgres.repository.OrderJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetActiveOrders {

    @Autowired
    private OrderJpaRepository repository;

    public List<Order> handle() {
        return repository.findByStatusNot(OrderStatus.COMPLETED).stream().map(entity -> OrderMapper.toDomain(entity)).collect(Collectors.toList());
    }
}
