package com.order.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.order.demo.entity.Pedido;

import org.springframework.beans.factory.annotation.Value;


@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Pedido> kafkaTemplate;
    private final String topic;

    public KafkaProducer(KafkaTemplate<String, Pedido> kafkaTemplate, @Value("${kafka.topic.orders}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendOrder(Pedido pedido) {
        kafkaTemplate.send(topic, pedido);
    }
}
