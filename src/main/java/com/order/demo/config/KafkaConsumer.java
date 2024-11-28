package com.order.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@Autowired
    private MessageStorage messageStorage;

    @KafkaListener(topics = "order-topic", groupId = "order-service-group")
    public void consumeMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
        messageStorage.addMessage(message); // Armazena a mensagem consumida
    }
    
}
