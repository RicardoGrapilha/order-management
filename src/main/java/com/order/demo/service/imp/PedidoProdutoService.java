package com.order.demo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.demo.entity.Pedido;
import com.order.demo.entity.PedidoProduto;
import com.order.demo.entity.Produto;
import com.order.demo.repository.PedidoProdutoRepository;
import com.order.demo.repository.ProdutoRepository;
import com.order.demo.service.IPedidoProdutoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoProdutoService   implements IPedidoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    public List<PedidoProduto> criarPedidoProdutos(Pedido pedido, List<PedidoProduto> pedidoProdutos) {
        List<PedidoProduto> resultado = new ArrayList<>();

        for (PedidoProduto pedidoProduto : pedidoProdutos) {
            Produto produto = produtoRepository.findById(pedidoProduto.getProduto().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: ID " + pedidoProduto.getProduto().getId()));

            if (produto.getEstoque() < pedidoProduto.getQuantidade()) {
                throw new IllegalArgumentException("Produto " + produto.getNome() + " possui apenas " + produto.getEstoque() + " unidades em estoque.");
            }

            // Atualiza o estoque do produto
            produto.setEstoque(produto.getEstoque() - pedidoProduto.getQuantidade());
            produtoRepository.save(produto);

            // Configura o pedido-produto
            pedidoProduto.setPedido(pedido);
            pedidoProduto.setPreco(produto.getPreco());
            resultado.add(pedidoProduto);
        }

        return pedidoProdutoRepository.saveAll(resultado);
    }
    public PedidoProduto salvarPedidoProduto(PedidoProduto pedidoProduto) {
    	return pedidoProdutoRepository.save(pedidoProduto);
    }
    public void salvarTodos(List<PedidoProduto> pedidoProdutos) {
        pedidoProdutoRepository.saveAll(pedidoProdutos);
    }
}
