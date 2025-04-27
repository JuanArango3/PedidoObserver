package org.example.pedidoobserver.subscriber.file.listener;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.pedidoobserver.common.dto.RegistroDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@Slf4j
public class SaveInFile {

    private final File file = new File("registros.txt");

    @RabbitListener(queues = "#{fileQueue.name}")
    public void onRegistroReceived(RegistroDTO registroDTO) {
        log.info("Guardando en el archivo el registro: {}", registroDTO.nombre());
        try (FileWriter writer = new FileWriter(file, true)) {
            String content = String.format("Nombre: %s, Identificacion: %s, Email: %s", registroDTO.nombre(), registroDTO.cedula(), registroDTO.email());
            writer.write(content + System.lineSeparator());
        } catch (IOException ex) {
            throw new RuntimeException("Error al guardar el registro en el archivo", ex);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @PostConstruct
    public void init() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            file.setWritable(true);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el archivo", e);
        }
    }
}
