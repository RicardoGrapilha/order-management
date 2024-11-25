package com.order.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.order.demo.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}