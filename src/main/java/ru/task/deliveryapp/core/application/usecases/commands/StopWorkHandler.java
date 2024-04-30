package ru.task.deliveryapp.core.application.usecases.commands;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;
import ru.task.deliveryapp.core.ports.CourierRepository;

@Service
public class StopWorkHandler {
    private final CourierRepository repository;

    @Autowired
    public StopWorkHandler(CourierRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void handle(StopWorkCommand command) {
        Courier courier = repository.get(command.courierId());
        courier.stopWork();
        repository.update(courier);
    }
}
