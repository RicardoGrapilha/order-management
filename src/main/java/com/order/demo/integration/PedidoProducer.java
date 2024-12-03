package com.order.demo.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.order.demo.entity.Pedido;

@Service
public class PedidoProducer {

    @Autowired
    private KafkaTemplate<String, Pedido> kafkaTemplate;

    public void enviarMensagem(Pedido mensagem) {
        kafkaTemplate.send("order-topic", mensagem);
    }
}
