package ru.task.deliveryapp.infrastructure.adapters.kafka;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.task.deliveryapp.core.ports.msg.OrderProducer;
import ru.task.deliveryapp.infrastructure.adapters.postgres.repository.OutboxJpaRepository;
import ru.task.deliveryapp.infrastructure.entity.OutboxEntity;

import java.util.UUID;


/**
 * Sending notifications to Kafka
 */
@Component
public class KafkaOrderProducer implements OrderProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaOrderProducer.class);

    @Value("${kafka.producer.topic}")
    private String topic;

    private final OutboxJpaRepository outboxRepository;

    private final KafkaTemplate<UUID, OrderIntegrationEvent> kafkaTemplate;

    @Autowired
    public KafkaOrderProducer(KafkaTemplate<UUID, OrderIntegrationEvent> kafkaTemplate, OutboxJpaRepository outboxRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.outboxRepository = outboxRepository;
    }

    @Override
    @Scheduled(fixedRateString = "${kafka.producer.schedule_rate}")
    @Transactional
    public void sendEvents() {
        for (OutboxEntity outbox: outboxRepository.findAll()) {
            log.info("Sending order status change notification into the topic {}", topic);
            kafkaTemplate.send(topic, OrderEventMapper.toIntegrationEvent(outbox.getEventPayload()));
            outboxRepository.deleteAll();
        }
    }
}
