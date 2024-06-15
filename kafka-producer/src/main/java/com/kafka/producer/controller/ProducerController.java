package com.kafka.producer.controller;

import com.kafka.producer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @PostMapping("/producer")
    private ResponseEntity<?> publishKafkaMessage(@RequestBody String message) {
        try {

            for (int i = 0; i < 1000; i++) {
                kafkaMessagePublisher.publishKafkaMessage(message + " : " + i);
            }

            return ResponseEntity.ok("message published");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
