package com.order.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.demo.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	//@Query("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.produtos")
	//List<Pedido> findPedidosComProdutos();
}