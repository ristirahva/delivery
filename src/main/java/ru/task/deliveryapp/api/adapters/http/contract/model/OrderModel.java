package ru.task.deliveryapp.api.adapters.http.contract.model;

import jakarta.validation.constraints.NotNull;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;

import java.util.UUID;

public record OrderModel(@NotNull UUID id, Location location) { }
