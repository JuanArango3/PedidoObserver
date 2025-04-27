package org.example.pedidoobserver.publisher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pedidoobserver.common.dto.RegistroDTO;
import org.example.pedidoobserver.publisher.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistroService {

    private final RabbitTemplate rabbitTemplate;

    public void createRegistro(RegistroDTO registroDTO) {
        log.info("Creando registro");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "routing.registro.new", registroDTO);
        log.info("Registro publicado en RabbitMQ");
    }
}
