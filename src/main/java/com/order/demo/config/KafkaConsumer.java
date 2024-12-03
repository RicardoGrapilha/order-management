package com.order.demo.config;



import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.order.demo.entity.Pedido;

@Service
public class KafkaConsumer {

    
    private final List<Pedido> pedidosCache = new ArrayList<>();

    @KafkaListener(topics = "${kafka.topic.orders}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Pedido pedido) {
        pedidosCache.removeIf(p -> p.getId().equals(pedido.getId())); // Atualizar caso o pedido já exista
        pedidosCache.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return new ArrayList<>(pedidosCache);
    }
    
}
