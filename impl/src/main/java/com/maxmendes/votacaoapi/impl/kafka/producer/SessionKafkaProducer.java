package com.maxmendes.votacaoapi.impl.kafka.producer;

import com.maxmendes.votacaoapi.impl.kafka.model.SessionKafkaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SessionKafkaProducer {

    private final String topic;
    private final KafkaTemplate<String, SessionKafkaModel> kafkaTemplate;

    public SessionKafkaProducer(@Value("${kafka.topic}") String topic,
                                KafkaTemplate<String, SessionKafkaModel> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(SessionKafkaModel sessionKafkaModel) {
        kafkaTemplate.send(topic, sessionKafkaModel).addCallback(
                success -> log.info("MESSAGE SENDED: {}", success.getProducerRecord().value()),
                failure -> log.info("FAILED SENDING MESSAGE: {}", sessionKafkaModel)
        );
    }
}
