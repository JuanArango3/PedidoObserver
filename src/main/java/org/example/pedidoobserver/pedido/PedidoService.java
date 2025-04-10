package org.example.pedidoobserver.pedido;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pedidoobserver.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final List<PedidoSubscriber> subscribers;

    private final RabbitTemplate rabbitTemplate;

    public void createPedido(PedidoDTO pedido) {
        log.info("Creando Pedido");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "routing.products.new", pedido);
        log.info("Pedido publicado en RabbitMQ");
        /*
        for (PedidoSubscriber subscriber : subscribers) {
            subscriber.onPedidoReceived(pedido);
        }
        */

    }

    @PostConstruct
    public void init() {
        //createPedido(new PedidoDTO("Producto 1", 2, 10.0f));
    }
}
