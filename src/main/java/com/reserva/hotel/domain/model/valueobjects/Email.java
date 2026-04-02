package com.reserva.hotel.domain.model.valueobjects;

import com.reserva.hotel.domain.exception.BusinessRuleException;

public final class Email {

    private final String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new BusinessRuleException("El email no puede estar vacío");
        }
        if (!value.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new BusinessRuleException("El email no tiene un formato válido: " + value);
        }
        this.value = value.toLowerCase();
    }

    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        return value.equals(((Email) o).value);
    }

    @Override
    public int hashCode() { return value.hashCode(); }

    @Override
    public String toString() { return value; }
}