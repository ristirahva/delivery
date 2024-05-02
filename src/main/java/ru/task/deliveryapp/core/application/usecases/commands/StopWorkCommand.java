package ru.task.deliveryapp.core.application.usecases.commands;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record StopWorkCommand(@NotNull UUID courierId) {
}
