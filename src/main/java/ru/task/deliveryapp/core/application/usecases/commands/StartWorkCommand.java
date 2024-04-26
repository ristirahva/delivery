package ru.task.deliveryapp.core.application.usecases.commands;

import java.util.UUID;

public class StartWorkCommand {
    public StartWorkCommand(UUID courierId) {
        this.courierId = courierId;
    }

    public UUID getCourierId() {
        return courierId;
    }

    private UUID courierId;
}
