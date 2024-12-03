package com.order.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
   
    @Getter
    @Setter
    private Double valorTotal;
    
    @Getter
    @Setter
    private String status; // Pendente, Processado, etc.
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private List<PedidoProduto> pedidoProdutos = new ArrayList<>();
    
    @Getter
    @Setter
    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

}
