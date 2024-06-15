package com.kafka.producer.service;

import com.kafka.producer.config.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> stringObjectKafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    String topic;

    public void publishKafkaMessage(String message){
        CompletableFuture<SendResult<String, Object>> future = stringObjectKafkaTemplate.send(topic,message);
        future.whenComplete((result,ex)->{
            if (ex==null){
                System.out.println("send message-["+
                        message+"] with partition=["+result.getRecordMetadata().partition()+
                        "] offset=["+result.getRecordMetadata().offset()+"]");
            }else {
                System.out.println(ex.getMessage());
            }
        });
    }

}
