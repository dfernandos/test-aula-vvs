package com.example.audioexercicio;

import java.util.List;

public class Pedido {

    private List<ItemPedido> itens;
    private DescontoService descontoService;

    public Pedido(List<ItemPedido> itens, DescontoService descontoService) {
        this.itens = itens;
        this.descontoService = descontoService;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public DescontoService getDescontoService() {
        return descontoService;
    }

    public double calcularValorTotal() {
        double valorTotal = 0.0;

        if(itens.equals(null)){
            return 0.0;
        }

        for (ItemPedido item : itens) {
            valorTotal += item.getSubtotal();
        }


        double desconto = descontoService.calcularDesconto(valorTotal);

        if(desconto>valorTotal){
            return 0.0;
        }
        return valorTotal - desconto;
    }

    public double calcularValorTotalPorItem(ItemPedido itemPedido) {

        double desconto = descontoService.calcularDesconto(itemPedido.getSubtotal());

        return itemPedido.getSubtotal() - desconto;
    }

    public double calcularValorTotalDescontoitens() {
        double valorTotal = 0.0;

        for (ItemPedido item : itens) {
            valorTotal += calcularValorTotalPorItem(item);
        }

        return valorTotal;
    }


}