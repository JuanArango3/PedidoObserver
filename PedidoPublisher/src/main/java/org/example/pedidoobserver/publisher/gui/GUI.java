package org.example.pedidoobserver.publisher.gui;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.pedidoobserver.common.dto.RegistroDTO;
import org.example.pedidoobserver.publisher.service.RegistroService;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@RequiredArgsConstructor
public class GUI {


    private final RegistroService registroService;

    @PostConstruct
    public void init() {
        new Thread(() -> {

            JOptionPane.showMessageDialog(null, "Bienvenid@ al sistema de inscripción de la U C&J. ¡Empecemos!",
                    "Bienvenida", JOptionPane.INFORMATION_MESSAGE);

            // Nombre
            String nombre = JOptionPane.showInputDialog(null, "Ingresa el nombre completo:",
                    "Inscripcion", JOptionPane.QUESTION_MESSAGE);

            //  Tipo de identificacion
            String[] tiposIdentificacion = {
                    "Cédula de ciudadanía",
                    "Tarjeta de identidad",
                    "Cédula de extranjería"
            };

            String tipoSeleccionado = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecciona tu tipo de identificación:",
                    "Tipo de Identificación",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tiposIdentificacion,
                    tiposIdentificacion[0]
            );

            if (tipoSeleccionado != null) {
                System.out.println("Nombre ingresado: " + nombre);
                System.out.println("Tipo de identificación seleccionado: " + tipoSeleccionado);
            } else {
                System.out.println("No seleccionaste tipo de identificación.");
            }

                //  Identificacion
                int identificacion = 0;
                while (true) {
                    try {
                        String IdStr = JOptionPane.showInputDialog(null, "Ingrese la identificacion:",
                                "Pedido", JOptionPane.QUESTION_MESSAGE);
                        identificacion = Integer.parseInt(IdStr);
                        break;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número de identificacion correcto sin espacios ni puntos",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            // Es Colombiano (Pregunta sí/no)
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Eres colombiano?",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            boolean esColombiano = (respuesta == JOptionPane.YES_OPTION);

            // Carrera
            String[] carreras = {
                    "Medicina veterinaria",
                    "Ingenieria informatica",
                    "Ingenieria ambiental",
                    "Derecho",
                    "Administracion"
            };

            String carreraSeleccionada = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecciona tu carrera:",
                    "Carrera",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    carreras,
                    carreras[0]
            );

            if (carreraSeleccionada == null) {
                JOptionPane.showMessageDialog(null, "No seleccionaste una carrera.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Correo electrónico: Validar que termine en @gmail.com o @hotmail.com
            String email;
            while (true) {
                email = JOptionPane.showInputDialog(null, "Ingresa tu correo electrónico:",
                        "Correo electrónico", JOptionPane.QUESTION_MESSAGE);

                if (email != null && (email.endsWith("@gmail.com") || email.endsWith("@hotmail.com"))) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "El correo debe terminar en @gmail.com o @hotmail.com",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }


            // Crear el RegistroDTO
            RegistroDTO registroDTO = RegistroDTO.builder()
                    .nombre(nombre)
                    .tipoIdentificacion(tipoSeleccionado)
                    .cedula(identificacion)
                    .esColombiano(esColombiano)
                    .carrera(carreraSeleccionada)
                    .email(email)
                    .build();

            // Llamar al servicio para procesar el registro
            registroService.createRegistro(registroDTO);

            // Mensaje de éxito
            JOptionPane.showMessageDialog(null, "Pre-registro creado exitosamente!",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);



            JOptionPane.showMessageDialog(null, "Gracias por usar el sistema de inscripción de la U C&J.",
                    "Finalizado", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }).start();

    }
}
