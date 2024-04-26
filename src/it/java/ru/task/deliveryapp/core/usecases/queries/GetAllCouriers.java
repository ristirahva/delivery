package ru.task.deliveryapp.core.usecases.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.infrastructure.adapters.postgres.CourierMapper;
import ru.task.deliveryapp.infrastructure.adapters.postgres.repository.CourierJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllCouriers {
    @Autowired
    CourierJpaRepository repository;

    public List<Courier> handle() {
        return repository.findAllByOrderByNameAsc().stream().map(entity -> CourierMapper.toDomain(entity)).collect(Collectors.toList());
    }
}
