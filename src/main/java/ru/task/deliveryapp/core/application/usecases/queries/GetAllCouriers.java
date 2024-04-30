package ru.task.deliveryapp.core.application.usecases.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.infrastructure.adapters.postgres.CourierMapper;
import ru.task.deliveryapp.infrastructure.adapters.postgres.repository.CourierJpaRepository;

import java.util.List;

@Service
public class GetAllCouriers {
    @Autowired
    private CourierJpaRepository repository;

    public List<Courier> handle() {
        return CourierMapper.listToDomain(repository.findAll());
    }
}
