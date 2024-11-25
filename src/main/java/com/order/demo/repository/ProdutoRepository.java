package com.order.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.order.demo.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}