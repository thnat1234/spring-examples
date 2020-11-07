package itx.examples.spring.kafka.producer.service;

import itx.examples.spring.kafka.events.AccountAsyncEvent;
import itx.examples.spring.kafka.events.DataMessageAsyncEvent;
import itx.examples.spring.kafka.events.AsyncEvent;
import itx.examples.spring.kafka.events.EventWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventProducer.class);
    private static final String TOPIC = "prod-con-test-topic";

    private final KafkaTemplate<String, EventWrapper<? extends AsyncEvent>> kafkaTemplate;

    public EventProducer(@Autowired KafkaTemplate<String, EventWrapper<? extends AsyncEvent>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(DataMessageAsyncEvent message) {
        LOGGER.info("#### Producing message: {} {}", message.getId(), message.getMessage());
        this.kafkaTemplate.send(TOPIC, new EventWrapper<>(message));
    }

    public void sendAccountMessage(AccountAsyncEvent accountAsyncEvent) {
        LOGGER.info("#### Producing account message: {}", accountAsyncEvent.getId());
        this.kafkaTemplate.send(TOPIC, new EventWrapper<>(accountAsyncEvent));
    }

}
