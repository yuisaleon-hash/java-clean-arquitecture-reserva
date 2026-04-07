package com.reserva.hotel.infrastructure.adapter.out.notification;

import com.reserva.hotel.application.port.out.EmailNotificationPort;
import com.reserva.hotel.shared.annotation.Adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Adapter
public class EmailNotificationAdapter implements EmailNotificationPort {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationAdapter.class);

    private final JavaMailSender mailSender;

    @Value("${reserva.hotel.notification.email.from-address}")
    private String fromAddress;

    public EmailNotificationAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendReservaConfirmacion(String email, String clienteNombre,
                                        String hotelNombre, String fechaInicio,
                                        String fechaFin, String reservaId) {

        enviarEmail(email, "Confirmación de Reserva",
                String.format(
                        "Estimado/a %s,\n\nReserva confirmada en %s\nDesde %s hasta %s\nID: %s",
                        clienteNombre, hotelNombre, fechaInicio, fechaFin, reservaId
                ));
    }

    @Override
    public void sendReservaRecordatorio(String email, String clienteNombre,
                                        String hotelNombre, String fechaInicio) {

        enviarEmail(email, "Recordatorio de Reserva",
                String.format(
                        "Hola %s,\nRecuerda tu reserva en %s el día %s",
                        clienteNombre, hotelNombre, fechaInicio
                ));
    }

    @Override
    public void sendReservaCancelacion(String email, String clienteNombre,
                                       String hotelNombre, String fechaInicio) {

        enviarEmail(email, "Cancelación de Reserva",
                String.format(
                        "Hola %s,\nTu reserva en %s para el %s ha sido cancelada",
                        clienteNombre, hotelNombre, fechaInicio
                ));
    }

    private void enviarEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromAddress);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            mailSender.send(message);
            logger.info("Email enviado a: {}", to);

        } catch (Exception e) {
            logger.error("Error enviando email a: {}", to, e);
        }
    }
}