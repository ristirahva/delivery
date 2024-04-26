package ru.task.deliveryapp.infrastructure.adapters.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.domain.aggregate.courier.CourierStatus;
import ru.task.deliveryapp.core.ports.CourierRepository;
import ru.task.deliveryapp.exception.DbException;
import ru.task.deliveryapp.infrastructure.adapters.postgres.entity.CourierEntity;
import ru.task.deliveryapp.infrastructure.adapters.postgres.repository.CourierJpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourierAdapter implements CourierRepository {

    @Autowired
    CourierJpaRepository repository;

    @Override
    public Courier add(Courier courier) {
        if ((courier.getId() == null) || repository.findById(courier.getId()).isEmpty()) {
            return CourierMapper.toDomain(repository.save(CourierMapper.toEntity(courier)));
        }
        else {
            throw new DbException("A new courier cannot be added because it exists already");
        }
    }

    @Override
    public void update(Courier courier) {
        if (courier.getId() != null && !repository.findById(courier.getId()).isEmpty()) {
            repository.save(CourierMapper.toEntity(courier));
        }
        else {
            throw new DbException("A courier cannot be updates because it doesn't exist");
        }
    }

    @Override
    public Courier get(UUID courierId) {
        CourierEntity courierEntity = repository.findById(courierId).get();
        return CourierMapper.toDomain(courierEntity);
    }

    @Override
    public List<Courier> getAllReady() {
        return repository.findByStatusOrderByNameAsc(CourierStatus.READY).stream().map(entity -> CourierMapper.toDomain(entity)).collect(Collectors.toList());
    }

    @Override
    public List<Courier> getAllBusy() {
        return repository.findByStatusOrderByNameAsc(CourierStatus.BUSY).stream().map(entity -> CourierMapper.toDomain(entity)).collect(Collectors.toList());
    }
}
