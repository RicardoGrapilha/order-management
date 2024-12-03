package com.order.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PedidoDTO {
	@Getter
    @Setter
	private List<ProdutoGrupoDTO> pedidoProdutos;

	@Getter
    @Setter
    public static class ProdutoGrupoDTO {
        private List<ProdutoDTO> produto;
    }
	
	@Getter
    @Setter
    public static class ProdutoDTO {
        private Long produto_id;
        private Integer quantidade;
    }
}

