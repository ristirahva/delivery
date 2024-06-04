package ru.task.deliveryapp.infrastructure.adapters.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task.deliveryapp.infrastructure.entity.OutboxEntity;

import java.util.UUID;

@Repository
public interface OutboxJpaRepository extends JpaRepository<OutboxEntity, UUID> {
}
