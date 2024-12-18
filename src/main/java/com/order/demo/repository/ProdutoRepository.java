package com.order.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.demo.entity.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}