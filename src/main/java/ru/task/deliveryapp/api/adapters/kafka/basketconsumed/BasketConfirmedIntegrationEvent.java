package ru.task.deliveryapp.api.adapters.kafka.basketconsumed;

import java.util.UUID;

public record BasketConfirmedIntegrationEvent(UUID BasketId, String Address, int Weight) {
}
