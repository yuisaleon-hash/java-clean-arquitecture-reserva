package com.reserva.hotel.application.port.out;

public interface SmsNotificationPort {
    void sendAppointmentReminder(String phoneNumber, String message);
}