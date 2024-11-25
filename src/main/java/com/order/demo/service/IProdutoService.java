package com.order.demo.service;

import java.util.List;

import com.order.demo.entity.Produto;

public interface IProdutoService {
	 Produto salvarProduto(Produto produto);

	 List<Produto> listarProdutos();
}
