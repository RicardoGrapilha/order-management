package com.order.demo.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.demo.entity.Pedido;
import com.order.demo.repository.PedidoRepository;
import com.order.demo.service.IPedidoService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService implements IPedidoService {
    
    private final PedidoRepository pedidoRepository;

    // Construtor para injeção manual (usado em testes)
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido processarPedido(Pedido pedido) {
        pedido.setValorTotal(
            pedido.getProduto().getPreco().multiply(
                BigDecimal.valueOf(pedido.getQuantidade())
            )
        );
        pedido.setStatus("Processado");
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
}