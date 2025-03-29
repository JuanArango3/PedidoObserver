package org.example.pedidoobserver.pedido;

public interface PedidoSubscriber {
    void onPedidoReceived(PedidoDTO pedido);
}
