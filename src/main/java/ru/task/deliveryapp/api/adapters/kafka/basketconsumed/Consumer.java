package ru.task.deliveryapp.api.adapters.kafka.basketconsumed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.application.usecases.commands.CreateOrderCommand;
import ru.task.deliveryapp.core.application.usecases.commands.CreateOrderHandler;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;

@Service
public class Consumer {
    @Autowired
    CreateOrderHandler createOrderHandler;

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    private static final String SUBSCRIBE_TOPIC = "basket.confirmed";
    private static final String SUBSCRIBE_GROUP = "NotificationConsumerGroup";

    @KafkaListener(topics = SUBSCRIBE_TOPIC, groupId = SUBSCRIBE_GROUP)
    public void listenBasket(BasketConfirmedIntegrationEvent message) {
        log.info("A new message in topic basket.confirmed: " + message);
        createOrderHandler.handle(new CreateOrderCommand(message.BasketId(), message.Address(), Weight.create(message.Weight())));
    }
}
