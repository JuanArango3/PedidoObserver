package org.example.pedidoobserver.pedido.subscribers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pedidoobserver.RabbitMQConfig;
import org.example.pedidoobserver.pedido.PedidoDTO;
import org.example.pedidoobserver.pedido.PedidoSubscriber;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@Slf4j
public class SaveInFile implements PedidoSubscriber {

    private final File file = new File("pedidos.txt");

    @Override
    @RabbitListener(queues = "#{fileQueue.name}")
    public void onPedidoReceived(PedidoDTO pedido) {
        log.info("Guardando en el archivo el pedido: {}", pedido.nombre());
        try (FileWriter writer = new FileWriter(file, true)) {
            String content = String.format("Nombre: %s, Cantidad: %d, Precio Unitario: %.2f", pedido.nombre(), pedido.cantidad(), pedido.precioUnitario());
            writer.write(content + System.lineSeparator());
        } catch (IOException ex) {
            throw new RuntimeException("Error al guardar el pedido en el archivo", ex);
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
