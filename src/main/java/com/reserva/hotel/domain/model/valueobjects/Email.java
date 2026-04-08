package com.reserva.hotel.domain.model.valueobjects;

import com.reserva.hotel.domain.exception.BusinessRuleException;

import java.util.regex.Pattern;

public class Email {

    private final String value;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new BusinessRuleException("El email no puede estar vacío");
        }

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new BusinessRuleException("Formato de email inválido");
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}