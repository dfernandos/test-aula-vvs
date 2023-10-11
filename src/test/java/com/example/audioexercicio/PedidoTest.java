package com.example.audioexercicio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoTest {

    @Mock
    DescontoService descontoService;

    /*
    *
    * 1 - Crie um teste unitário para a classe Pedido que configure um mock do
    * DescontoService para simular um desconto de 10% e verifique se o método
    * calcularValorTotal retorna o valor correto após aplicar o desconto.
    * */

    Pedido pedido;


    @Test
    public void calcularValorTotalDeveRetornarComDescontoDe10Porcento() {

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(new ItemPedido(50));
        itemPedidoList.add(new ItemPedido(50));

        when(descontoService.calcularDesconto(100)).thenReturn(10.0);

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotal();
        assertEquals(90, valorTotal);
    }


    /*
    *
    * 2- Crie um teste unitário para a classe Pedido que simule um cenário em que o
    * desconto seja zero e
    *  verifique se o método calcularValorTotal retorna o valor correto sem desconto.
    * */
    @Test
    public void calcularValorTotalDeveRetornarZero() {

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(new ItemPedido(50));
        itemPedidoList.add(new ItemPedido(50));

        when(descontoService.calcularDesconto(100)).thenReturn(0.0);

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotal();
        assertEquals(100.0, valorTotal);

    }


    /*
    *
    * 3 - Crie outro teste unitário que simule um cenário em
    * que o desconto seja maior que o valor total do pedido e verifique
    * se o método calcularValorTotal retorna 0.0, ou seja, o valor total não pode ser negativo.
    * */
    @Test
    public void verificaValorTotalNaoPodeSerMenorQueZero() {

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(new ItemPedido(50));
        itemPedidoList.add(new ItemPedido(50));

        when(descontoService.calcularDesconto(100)).thenReturn(120.0);

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotal();
        assertEquals(0.0, valorTotal);

    }

    /*
    *
    * 4 - Adicione uma validação no método calcularValorTotal da classe Pedido para
    * lançar uma exceção IllegalArgumentException se o valor total for negativo após
    * aplicar o desconto.
    * Crie um teste unitário que verifique se essa exceção é lançada corretamente.
    *
    * */

    @Test
    public void verificaValorTotalDeveRetornarUmaExcecaoSeOServicoRetornarMenorQueZero() {

        when(descontoService.calcularDesconto(100)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, ()-> descontoService.calcularDesconto(100));

    }

    /*5 - Modifique a classe Pedido para aceitar uma lista vazia de itens.
     Crie um teste unitário que simule esse cenário e verifique se o método
     calcularValorTotal retorna 0.0 quando não há itens no pedido.
     */

    @Test
    public void verificaValorTotalDeveRetornarZeroSeAListaDeItensEstiverVazia() {

        List<ItemPedido> itemPedidoList = new ArrayList<>();

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotal();
        assertEquals(0.0, valorTotal);
    }

    /*
    *
    * 6 - Crie um teste unitário que envolva a classe Pedido
    * e dois mocks de DescontoService diferentes. Configure esses
    * mocks para simular diferentes valores de desconto para diferentes itens do
    * pedido e
    * verifique se o método calcularValorTotal calcula o valor
    * total corretamente com base nos descontos de cada item.*/


    @Test
    public void verificaValorTotalDeCadaItemDoPedido() {

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(new ItemPedido(100));
        itemPedidoList.add(new ItemPedido(100));

        when(descontoService.calcularDesconto(anyDouble())).thenReturn(10.0, 10.0);

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotalDescontoitens();
        assertEquals(180, valorTotal);

    }

    /*
    *
    * 7 - Modifique a classe Pedido para registrar o número de vezes que
    * o método calcularDesconto do DescontoService é chamado. Crie um teste
    * unitário que verifique se o método calcularValorTotal chama o método
    * calcularDesconto exatamente uma vez.
    * */

    @Test
    public void calcularValorTotalDescontoDeveSerChamadoSomenteUmaVez() {

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(new ItemPedido(50));
        itemPedidoList.add(new ItemPedido(50));

        when(descontoService.calcularDesconto(100)).thenReturn(10.0);

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotal();

        verify(descontoService, times(1)).calcularDesconto(100);

        assertEquals(90, valorTotal);
    }
}
