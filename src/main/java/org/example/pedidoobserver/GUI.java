package org.example.pedidoobserver;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.pedidoobserver.pedido.PedidoDTO;
import org.example.pedidoobserver.pedido.PedidoService;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@RequiredArgsConstructor
public class GUI {


    private final PedidoService pedidoService;

    @PostConstruct
    public void init() {

        boolean agregarOtroPedido;

        do {
            // Nombre
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto:",
                    "Pedido", JOptionPane.QUESTION_MESSAGE);

            //  Cantidad
            int cantidad = 0;
            while (true) {
                try {
                    String cantidadStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad:",
                            "Pedido", JOptionPane.QUESTION_MESSAGE);
                    cantidad = Integer.parseInt(cantidadStr);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número entero válido.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            //  Precio unitario
            float precioUnitario = 0.0f;
            while (true) {
                try {
                    String precioStr = JOptionPane.showInputDialog(null, "Ingrese el precio unitario:",
                            "Pedido", JOptionPane.QUESTION_MESSAGE);
                    precioUnitario = Float.parseFloat(precioStr);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el precio.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Crear el PedidoDTO y enviarlo al servicio
            PedidoDTO pedido = new PedidoDTO(nombre, cantidad, precioUnitario);
            pedidoService.createPedido(pedido);

            JOptionPane.showMessageDialog(null, "Pedido creado exitosamente!",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Agregar otro pedido
            int respuesta = JOptionPane.showConfirmDialog(null,
                    "¿Desea agregar otro pedido?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            agregarOtroPedido = (respuesta == JOptionPane.YES_OPTION);

        } while (agregarOtroPedido);

        JOptionPane.showMessageDialog(null, "Gracias por usar el sistema de pedidos.",
                "Finalizado", JOptionPane.INFORMATION_MESSAGE);
    }
}
