package org.example.pedidoobserver.pedido;

import java.io.Serializable;

/**
 * DTO for {@link Pedido}
 */
public record PedidoDTO(String nombre, int cantidad, float precioUnitario) implements Serializable {
}