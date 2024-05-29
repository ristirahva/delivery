package ru.task.deliveryapp.api.adapters.kafka.basketconsumed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.core.application.usecases.commands.CreateOrderCommand;
import ru.task.deliveryapp.core.application.usecases.commands.CreateOrderHandler;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;

@Service
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    private CreateOrderHandler createOrderHandler;

    @Autowired
    public Consumer(CreateOrderHandler createOrderHandler) {
        this.createOrderHandler = createOrderHandler;
    }

    @KafkaListener(topics = "${kafka.consumer.topic}", groupId = "${kafka.consumer.group}")
    public void listenBasket(BasketConfirmedIntegrationEvent message) {
        log.info("A new message in basket topic: " + message);
        createOrderHandler.handle(new CreateOrderCommand(message.BasketId(), message.Address(), Weight.create(message.Weight())));
    }
}
