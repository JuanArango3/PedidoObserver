package org.example.pedidoobserver.subscriber.db.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pedidoobserver.common.dto.RegistroDTO;
import org.example.pedidoobserver.subscriber.db.entity.Registro;
import org.example.pedidoobserver.subscriber.db.repository.RegistroRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveInDB {

    private final RegistroRepository registroRepository;


    @RabbitListener(queues = "#{dbQueue.name}")
    public void onRegistroReceived(RegistroDTO pedido) {
        log.info("Guardando en la base de datos el pedido: {}", pedido.nombre());
        Registro registroEntity = new Registro();
        registroEntity.setNombre(pedido.nombre());
        registroEntity.setCedula(String.valueOf(pedido.cedula()));
        registroEntity.setEsColombiano(pedido.esColombiano());
        registroEntity.setNombre(pedido.tipoIdentificacion());

        registroEntity.setCarrera(pedido.carrera());
        registroEntity.setEmail(pedido.email());
        registroRepository.save(registroEntity);
    }
}
