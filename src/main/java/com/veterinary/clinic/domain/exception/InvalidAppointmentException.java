package com.veterinary.clinic.domain.exception;

public class InvalidAppointmentException extends DomainException {

    public InvalidAppointmentException(String message) {
        super(message);
    }
}