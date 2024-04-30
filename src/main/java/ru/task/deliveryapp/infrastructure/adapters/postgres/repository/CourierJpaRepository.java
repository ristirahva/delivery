package ru.task.deliveryapp.infrastructure.adapters.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task.deliveryapp.infrastructure.adapters.postgres.entity.CourierEntity;
import ru.task.deliveryapp.core.domain.aggregate.courier.CourierStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourierJpaRepository extends JpaRepository<CourierEntity, UUID> {
    List<CourierEntity> findByStatus(CourierStatus status);
}
