package com.reserva.hotel.application.port.out;

public interface EmailNotificationPort {
    void sendAppointmentConfirmation(String email, String ownerName,
                                     String petName, String appointmentDate);

    void sendAppointmentReminder(String email, String ownerName,
                                 String petName, String appointmentDate);

    void sendAppointmentCancellation(String email, String ownerName,
                                     String petName, String appointmentDate);
}