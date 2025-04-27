package org.example.pedidoobserver.common.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record RegistroDTO(
        String nombre,
        String tipoIdentificacion,

        int cedula,
        boolean esColombiano,
        String carrera,

        String email

) implements Serializable {
}