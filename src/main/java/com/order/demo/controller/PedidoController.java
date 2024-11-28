package com.order.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.demo.config.KafkaProducer;
import com.order.demo.config.MessageStorage;
import com.order.demo.entity.Pedido;
import com.order.demo.service.imp.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private MessageStorage messageStorage; // Armazena mensagens consumidas para recuperação via endpoint


    
    
    private void setMessage( String orderDetails) {
    	 kafkaProducer.sendMessage("order-topic", orderDetails);
    }
    @GetMapping("/get-messages")
    public List<String> getMessages() {
        return messageStorage.getMessages();
    }
    

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
    	Pedido pedidoProcessado = pedidoService.processarPedido(pedido);
    	setMessage(pedidoProcessado.toString());
        return ResponseEntity.ok(
        		pedidoProcessado
        		);
    }
}
