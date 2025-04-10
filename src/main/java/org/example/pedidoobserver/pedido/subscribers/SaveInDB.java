package org.example.pedidoobserver.pedido.subscribers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pedidoobserver.pedido.Pedido;
import org.example.pedidoobserver.pedido.PedidoDTO;
import org.example.pedidoobserver.pedido.PedidoRepository;
import org.example.pedidoobserver.pedido.PedidoSubscriber;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveInDB implements PedidoSubscriber {

    private final PedidoRepository pedidoRepository;


    @Override
    @RabbitListener(queues = "#{dbQueue.name}")
    public void onPedidoReceived(PedidoDTO pedido) {
        log.info("Guardando en la base de datos el pedido: {}", pedido.nombre());
        Pedido pedidoEntity = new Pedido();
        pedidoEntity.setNombre(pedido.nombre());
        pedidoEntity.setCantidad(pedido.cantidad());
        pedidoEntity.setPrecioUnitario(pedido.precioUnitario());
        pedidoRepository.save(pedidoEntity);
    }
}
