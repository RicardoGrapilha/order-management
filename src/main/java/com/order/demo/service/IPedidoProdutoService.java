package com.order.demo.service;


import java.util.List;

import com.order.demo.entity.Pedido;
import com.order.demo.entity.PedidoProduto;
public interface IPedidoProdutoService  {
	
	List<PedidoProduto> criarPedidoProdutos(Pedido pedido, List<PedidoProduto> pedidoProdutos);
	PedidoProduto salvarPedidoProduto(PedidoProduto pedidoProduto);
	void salvarTodos(List<PedidoProduto> pedidoProdutos);
}
