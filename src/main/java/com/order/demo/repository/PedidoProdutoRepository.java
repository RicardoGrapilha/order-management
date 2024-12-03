package com.order.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.order.demo.entity.PedidoProduto;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {
}
