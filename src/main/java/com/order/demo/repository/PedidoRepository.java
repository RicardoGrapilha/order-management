package com.order.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.order.demo.entity.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	@Query("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.produtos")
	List<Pedido> findPedidosComProdutos();
}