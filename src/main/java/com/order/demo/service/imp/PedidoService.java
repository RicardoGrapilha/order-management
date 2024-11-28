package com.order.demo.service.imp;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.demo.entity.Pedido;
import com.order.demo.entity.Produto;
import com.order.demo.repository.PedidoRepository;
import com.order.demo.service.IPedidoService;

import jakarta.persistence.EntityManager;

import java.util.List;

@Service
public class PedidoService implements IPedidoService {
	@Autowired
	private EntityManager entityManager;
    
    private final PedidoRepository pedidoRepository;

    // Construtor para injeção manual (usado em testes)
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido processarPedido(Pedido pedido) {
    	
    	pedido.setValorTotal(
		    pedido.getProdutos().stream()
		          .mapToDouble(produto -> produto.getPreco() * produto.getQuantidade())
		          .sum()
		);
    	// Preenche o campo "pedido" de cada produto (se necessário)
        for (Produto produto : pedido.getProdutos()) {
            produto.setPedido(pedido);  // Associa o pedido ao produto
        }
        
        pedido.setStatus("Processado");
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
    	 List<Pedido> pedidos = pedidoRepository.findAll();

    	 pedidos.forEach(p -> Hibernate.initialize(p.getProdutos())); // Inicializa apenas quando necessário
    	    

    	    return pedidos;
    }
}