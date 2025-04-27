package org.example.pedidoobserver.common.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record RegistroDTO(
        String nombre,
        
        // CC 9124891274 o TI 124871284
        String cedula,
        boolean esColombiano,
        String carrera,
        String email

) implements Serializable {
}