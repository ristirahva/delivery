package ru.task.deliveryapp.core.application.usecases.commands;

import ru.task.deliveryapp.core.domain.sharedkernel.Weight;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateOrderCommand(@NotNull UUID basketId, String address, Weight weight) {
}
