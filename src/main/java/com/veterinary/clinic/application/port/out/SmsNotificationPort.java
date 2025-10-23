package com.veterinary.clinic.application.port.out;

public interface SmsNotificationPort {
    void sendAppointmentReminder(String phoneNumber, String message);
}