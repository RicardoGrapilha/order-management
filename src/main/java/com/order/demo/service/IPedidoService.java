package com.order.demo.service;


import java.util.List;


import com.order.demo.entity.Pedido;


public interface IPedidoService  {
	
	Pedido processarPedido(Pedido pedido);

    List<Pedido> listarTodos();
    Pedido salvarPedido(Pedido pedido);
}
