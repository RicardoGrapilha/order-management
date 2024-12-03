package com.order.demo.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.demo.entity.Produto;
import com.order.demo.repository.ProdutoRepository;
import com.order.demo.service.IProdutoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService  implements IProdutoService  {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }


    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }
    public List<Produto> buscarPorIds(List<Long> ids) {
        return produtoRepository.findAllById(ids);
    }
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }
    
}
