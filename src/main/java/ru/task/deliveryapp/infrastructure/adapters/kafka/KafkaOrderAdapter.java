package ru.task.deliveryapp.infrastructure.adapters.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.task.deliveryapp.core.domain.aggregate.order.Order;
import ru.task.deliveryapp.core.domain.aggregate.order.events.OrderDomainEvent;
import ru.task.deliveryapp.core.ports.msg.Producer;

import java.util.UUID;

/**
 * Sending notifications to Kafka
 */
@Component
public class KafkaOrderAdapter implements Producer {

    private static final Logger log = LoggerFactory.getLogger(KafkaOrderAdapter.class);

    @Value("${kafka.producer.topic}")
    private String topic;

    private KafkaTemplate<UUID, OrderIntegrationEvent> kafkaTemplate;

    @Autowired
    public KafkaOrderAdapter(KafkaTemplate<UUID, OrderIntegrationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendEvents(Order order) {
        for (OrderDomainEvent domainEvent: order.getDomainEvents()) {
            log.info("Sending order status change notification into the topic {}", topic);
            kafkaTemplate.send(topic, OrderEventMapper.toIntegrationEvent(domainEvent));
        }
    }
}
