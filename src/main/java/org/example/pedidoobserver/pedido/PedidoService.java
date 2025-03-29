package org.example.pedidoobserver.pedido;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final List<PedidoSubscriber> subscribers;

    public void createPedido(PedidoDTO pedido) {
        for (PedidoSubscriber subscriber : subscribers) {
            subscriber.onPedidoReceived(pedido);
        }
    }

    @PostConstruct
    public void init() {
        createPedido(new PedidoDTO("Producto 1", 2, 10.0f));
    }
}
