package com.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Value("${spring.kafka.producer.topic}")
    String topic;

    @Value("${spring.kafka.producer.partition}")
    Integer partition;

    @Value("${spring.kafka.producer.replication}")
    int replication;

    @Bean
    public NewTopic createTopic() {

        return new NewTopic(topic, partition, (short) replication);
    }
}
