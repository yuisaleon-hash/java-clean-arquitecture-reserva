package com.reserva.hotel.domain.exception;

public class BusinessRuleException extends DomainException {

    public BusinessRuleException(String message) {
        super("BUSINESS_RULE", message);
    }
}