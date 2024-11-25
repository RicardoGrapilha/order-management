package com.order.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
   
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    @Getter
    @Setter
    private Produto produto;
    
    @Getter
    @Setter
    private Integer quantidade;
    
    @Getter
    @Setter
    private BigDecimal valorTotal;
    
    @Getter
    @Setter
    private String status; // Pendente, Processado, etc.
}
