//package com.maxmendes.votacaoapi.impl.kafka.config;
//
//import com.maxmendes.votacaoapi.impl.kafka.model.SessionKafkaModel;
//import com.maxmendes.votacaoapi.impl.model.entity.SessionEntity;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaProducerConfig {
//
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrap;
//
//    @Value("${kafka.topic}")
//    private String topic;
//
//    @Bean
//    public NewTopic createTopic() {
//        return new NewTopic(topic, 3, (short) 1);
//    }
//
//    @Bean
//    public ProducerFactory<String, SessionKafkaModel> sessionEntityProducerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, SessionKafkaModel> sessionKafkaTemplate() {
//        return new KafkaTemplate<>(sessionEntityProducerFactory());
//    }
//}
