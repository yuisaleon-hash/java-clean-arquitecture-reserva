package com.veterinary.clinic.domain.exception;

public class AppointmentConflictException extends DomainException {

    public AppointmentConflictException(String message) {
        super(message);
    }
}