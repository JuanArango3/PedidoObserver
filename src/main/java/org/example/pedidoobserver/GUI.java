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

        // Aqui crea una ventana para pedir los datos de PedidoDTO
        JOptionPane.showMessageDialog(null, "Prueba de GUI", "Pedido Observer", JOptionPane.INFORMATION_MESSAGE);


        // Despues de que el usuario ingrese los datos, crea un PedidoDTO y lo envía al servicio
        // ejemplo: pedidoService.createPedido(new PedidoDTO("nombre", 1, 1.0f));
    }

}
