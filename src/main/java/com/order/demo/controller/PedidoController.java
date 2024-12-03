package com.order.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.demo.config.KafkaConsumer;
//import com.order.demo.config.KafkaProducer;
import com.order.demo.dto.PedidoDTO;
import com.order.demo.entity.Pedido;
import com.order.demo.entity.PedidoProduto;
import com.order.demo.service.imp.PedidoProdutoService;
import com.order.demo.service.imp.PedidoService;
import com.order.demo.service.imp.ProdutoService;
import com.order.demo.entity.Produto;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    /*@Autowired
    private KafkaProducer kafkaProducer;*/
    @Autowired
    private KafkaConsumer kafkaConsumerService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private PedidoProdutoService pedidoProdutoService;

    
    
    /*private void setMessage( Pedido orderDetails) {
    	 kafkaProducer.sendOrder(orderDetails);
    }*/
    @GetMapping("/get-messages")
    public List<Pedido> getMessages() {
        return kafkaConsumerService.getPedidos();
    }
    

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    
	
	@PostMapping
	public ResponseEntity<?> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
	    try {
	    	if (pedidoDTO.getPedidoProdutos() == null || pedidoDTO.getPedidoProdutos().isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pedido não contém produtos.");
	        }

	        // Criar e salvar o pedido inicial
	        Pedido pedido = new Pedido();
	        pedido.setDataCriacao(LocalDateTime.now());

	        List<PedidoProduto> pedidoProdutos = new ArrayList<>();
	        double valorTotal = 0.0;

	        for (PedidoDTO.ProdutoGrupoDTO grupo : pedidoDTO.getPedidoProdutos()) {
	            for (PedidoDTO.ProdutoDTO produtoDTO : grupo.getProduto()) {
	                // Buscar produto no banco
	                Produto produto = produtoService.buscarPorId(produtoDTO.getProduto_id())
	                    .orElseThrow(() -> new RuntimeException("Produto com ID " + produtoDTO.getProduto_id() + " não encontrado"));

	                // Verificar estoque
	                if (produto.getQuantidade() < produtoDTO.getQuantidade()) {
	                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                        .body("Produto com ID " + produtoDTO.getProduto_id() + " possui apenas " 
	                              + produto.getQuantidade() + " em estoque.");
	                }

	                // Criar PedidoProduto
	                PedidoProduto pedidoProduto = new PedidoProduto();
	                pedidoProduto.setPedido(pedido);
	                pedidoProduto.setProduto(produto);
	                pedidoProduto.setQuantidade(produtoDTO.getQuantidade());
	                pedidoProduto.setPreco(produto.getPreco() * produtoDTO.getQuantidade());

	                // Atualizar estoque
	                produto.setQuantidade(produto.getQuantidade() - produtoDTO.getQuantidade());
	                produtoService.salvarProduto(produto);

	                // Incrementar o valor total
	                valorTotal += pedidoProduto.getPreco();

	                pedidoProdutos.add(pedidoProduto);
	            }
	        }

	        
	        // Salvar o pedido com o valor total
            pedido.setValorTotal(valorTotal);
            Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);

            // Salvar os PedidoProdutos relacionados
            pedidoProdutos.forEach(p -> p.setPedido(pedidoSalvo)); // Relaciona ao pedido salvo
            pedidoProdutoService.salvarTodos(pedidoProdutos);

            return ResponseEntity.ok(pedidoProdutos);
            
            
	
	    } catch (IllegalArgumentException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    } catch (Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o pedido: " + ex.getMessage());
	    }
	}

    

}
