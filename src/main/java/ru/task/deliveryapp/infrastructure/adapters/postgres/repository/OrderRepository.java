package ru.task.deliveryapp.infrastructure.adapters.postgres.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;;
import ru.task.deliveryapp.infrastructure.adapters.postgres.entity.OrderEntity;
import ru.task.deliveryapp.core.domain.aggregate.order.OrderStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, UUID> {
    List<OrderEntity> findByStatus(OrderStatus status);
    List<OrderEntity> findByStatusNot(OrderStatus status);
}
