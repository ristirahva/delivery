package ru.task.deliveryapp.core.application.usecases.commands;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.ports.db.CourierRepository;

@Service
public class StartWorkHandler {
    private final CourierRepository repository;

    @Autowired
    public StartWorkHandler(CourierRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void handle(StartWorkCommand command) {
        var courier = repository.get(command.courierId());
        courier.startWork();
        repository.update(courier);
    }
}
