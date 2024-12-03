package com.order.demo.service;

import java.util.List;
import java.util.Optional;

import com.order.demo.entity.Produto;

public interface IProdutoService {
	 Produto salvarProduto(Produto produto);

	 List<Produto> listarProdutos();
	 List<Produto> buscarPorIds(List<Long> ids);
	 Optional<Produto> buscarPorId(Long id);
}
