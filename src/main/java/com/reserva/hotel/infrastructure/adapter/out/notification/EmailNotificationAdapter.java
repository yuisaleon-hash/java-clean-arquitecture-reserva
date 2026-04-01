package com.reserva.hotel.infrastructure.adapter.out.notification;

import com.reserva.hotel.application.port.out.EmailNotificationPort;
import com.reserva.hotel.shared.annotation.Adapter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Adapter
public class EmailNotificationAdapter implements EmailNotificationPort {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationAdapter.class);

    private final JavaMailSender mailSender;

    public EmailNotificationAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendAppointmentConfirmation(String email, String ownerName,
                                            String petName, String appointmentDate) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Confirmación de Cita - Clínica Veterinaria");
            message.setText(buildConfirmationMessage(ownerName, petName, appointmentDate));

            mailSender.send(message);
            logger.info("Email de confirmación enviado a: {}", email);
        } catch (Exception e) {
            logger.error("Error enviando email de confirmación a: {}", email, e);
        }
    }

    @Override
    public void sendAppointmentReminder(String email, String ownerName,
                                        String petName, String appointmentDate) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Recordatorio de Cita - Clínica Veterinaria");
            message.setText(buildReminderMessage(ownerName, petName, appointmentDate));

            mailSender.send(message);
            logger.info("Email de recordatorio enviado a: {}", email);
        } catch (Exception e) {
            logger.error("Error enviando email de recordatorio a: {}", email, e);
        }
    }

    @Override
    public void sendAppointmentCancellation(String email, String ownerName,
                                            String petName, String appointmentDate) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Cancelación de Cita - Clínica Veterinaria");
            message.setText(buildCancellationMessage(ownerName, petName, appointmentDate));

            mailSender.send(message);
            logger.info("Email de cancelación enviado a: {}", email);
        } catch (Exception e) {
            logger.error("Error enviando email de cancelación a: {}", email, e);
        }
    }

    private String buildConfirmationMessage(String ownerName, String petName, String appointmentDate) {
        return String.format(
                "Estimado/a %s,\n\n" +
                        "Su cita para %s ha sido confirmada para el %s.\n\n" +
                        "Gracias por confiar en nuestra clínica veterinaria.\n\n" +
                        "Saludos cordiales,\n" +
                        "Clínica Veterinaria",
                ownerName, petName, appointmentDate
        );
    }

    private String buildReminderMessage(String ownerName, String petName, String appointmentDate) {
        return String.format(
                "Estimado/a %s,\n\n" +
                        "Le recordamos que tiene una cita programada para %s el %s.\n\n" +
                        "Por favor, llegue 10 minutos antes de su cita.\n\n" +
                        "Saludos cordiales,\n" +
                        "Clínica Veterinaria",
                ownerName, petName, appointmentDate
        );
    }

    private String buildCancellationMessage(String ownerName, String petName, String appointmentDate) {
        return String.format(
                "Estimado/a %s,\n\n" +
                        "Su cita para %s programada para el %s ha sido cancelada.\n\n" +
                        "Si desea reprogramar, por favor contáctenos.\n\n" +
                        "Saludos cordiales,\n" +
                        "Clínica Veterinaria",
                ownerName, petName, appointmentDate
        );
    }
}