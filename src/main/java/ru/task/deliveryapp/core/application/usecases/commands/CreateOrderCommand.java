package ru.task.deliveryapp.core.application.usecases.commands;

import ru.task.deliveryapp.core.domain.sharedkernel.Weight;

import java.util.UUID;

public class CreateOrderCommand {
    private UUID basketId;
    private String address;
    private Weight weight;

    public CreateOrderCommand(UUID basketId, String address, Weight weight) {
        this.basketId = basketId;
        this.address = address;
        this.weight = weight;
    }

    public UUID getBasketId() {
        return basketId;
    }

    public String getAddress() {
        return address;
    }

    public Weight getWeight() {
        return weight;
    }
}
