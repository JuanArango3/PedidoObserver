package org.example.pedidoobserver.subscriber.file.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pedidoobserver.common.dto.RegistroDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendEmail {

    private final JavaMailSender mailSender;

    @Value("${pedido.email.recipient}")
    private String recipientEmail;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @RabbitListener(queues = "#{fileQueue.name}")
    public void onRegistroReceived(RegistroDTO registro) {
        log.info("Recibido registro para enviar por email: {}", registro.nombre());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(recipientEmail);
            message.setSubject("Nuevo Registro Recibido: " + registro.nombre());
            message.setText(buildEmailBody(registro));

            mailSender.send(message);
            log.info("Correo de registro enviado exitosamente a {}", recipientEmail);

        } catch (MailException e) {
            log.error("Error al enviar el correo para el registro {}: {}", registro.nombre(), e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al procesar el mensaje del registro {}: {}", registro.nombre(), e.getMessage(), e);
        }
    }

    private String buildEmailBody(RegistroDTO registro) {
        return String.format(
                """
                        Se ha recibido un nuevo registro:
                        
                        Nombre: %s
                        Tipo ID: %s
                        Número ID: %d
                        Email: %s
                        Carrera: %s
                        Colombiano: %s
                        """,
                registro.nombre(),
                registro.tipoIdentificacion(),
                registro.cedula(),
                registro.email(),
                registro.carrera(),
                registro.esColombiano() ? "Sí" : "No"
        );
    }
}
