package ru.task.deliveryapp.infrastructure.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.task.deliveryapp.core.domain.aggregate.order.events.OrderDomainEvent;

public class DomainEventConverter implements AttributeConverter<OrderDomainEvent, String> {

    private static final Logger log = LoggerFactory.getLogger(DomainEventConverter.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(OrderDomainEvent orderDomainEvent) {
        try {
            return objectMapper.writeValueAsString(orderDomainEvent);
        } catch (JsonProcessingException jpe) {
            log.warn("Cannot convert OrderDomainEvent into JSON");
            return null;
        }
    }

    @Override
    public OrderDomainEvent convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, OrderDomainEvent.class);
        } catch (JsonProcessingException e) {
            log.warn("Cannot convert JSON into OrderDomainEvent");
            return null;
        }
    }
}
