package ru.task.deliveryapp.api.adapters.kafka.basketconsumed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.task.deliveryapp.ContractKafka;

@Service
public class Consumer {
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    @KafkaListener(topics = "basket.confirmed", groupId = "NotificationConsumerGroup")
    //public void listenBasket(ContractKafka.BasketConfirmedIntegrationEvent message) {
    public void listenBasket(BasketConfirmedIntegrationEvent message) {
        log.info("!!!!! Received Message in topic basket.confirmed: " + message);
    }
}
