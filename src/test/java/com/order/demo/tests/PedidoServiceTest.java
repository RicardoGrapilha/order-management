package com.order.demo.tests;



import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.order.demo.entity.Pedido;
import com.order.demo.entity.Produto;
import com.order.demo.repository.PedidoRepository;
import com.order.demo.service.imp.PedidoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoServiceTest {
	@Autowired
	private PedidoRepository pedidoRepository;

    @Test
    public void testProcessarPedido() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setPreco(Double.valueOf(100));

        Pedido pedido = new Pedido();
     

        
        PedidoRepository mockRepository = Mockito.mock(pedidoRepository);
        Mockito.when(mockRepository.save(Mockito.any(Pedido.class)))
               .thenAnswer(invocation -> invocation.getArgument(0)); // Simula o retorno do save()

        PedidoService pedidoService = new PedidoService(mockRepository);

        Pedido pedidoProcessado = pedidoService.processarPedido(pedido);

        assertEquals(Double.valueOf(200), pedidoProcessado.getValorTotal());
        assertEquals("Processado", pedidoProcessado.getStatus());
    }
    @Test
    public void testProcessarMaisDe1500Pedidos() {
       
        PedidoRepository mockRepository = Mockito.mock(PedidoRepository.class);

       
        Mockito.when(mockRepository.save(Mockito.any(Pedido.class)))
               .thenAnswer(invocation -> invocation.getArgument(0));

        
        PedidoService pedidoService = new PedidoService(mockRepository);

       
        List<Pedido> pedidos = new ArrayList<>();
        int quantidadePedidos = 1500;

        for (int i = 1; i <= quantidadePedidos; i++) {
            // Criar produtos com preços variando de R$10 a R$100
            Produto produto = new Produto();
            produto.setId((long) i);
            produto.setNome("Produto " + i);
            produto.setPreco(Double.valueOf(10 + (i % 91))); // Preço entre 10 e 100

            
            Pedido pedido = new Pedido();
           
            

            pedidos.add(pedido);
        }

        
        List<Pedido> pedidosProcessados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            pedidosProcessados.add(pedidoService.processarPedido(pedido));
        }

       
        for (int i = 0; i < quantidadePedidos; i++) {
            Pedido pedido = pedidos.get(i);
            Pedido pedidoProcessado = pedidosProcessados.get(i);

            Double valorEsperado = pedido.getProdutos().stream().mapToDouble(Produto::getPreco).sum();
           

            assertEquals(valorEsperado, pedidoProcessado.getValorTotal(), "Erro no valor total do pedido");
            assertEquals("Processado", pedidoProcessado.getStatus(), "Erro no status do pedido");
        }

        Mockito.verify(mockRepository, Mockito.times(quantidadePedidos)).save(Mockito.any(Pedido.class));
    }
}

