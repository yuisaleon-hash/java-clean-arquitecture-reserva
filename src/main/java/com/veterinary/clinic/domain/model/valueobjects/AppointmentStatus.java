package com.veterinary.clinic.domain.model.valueobjects;

public enum AppointmentStatus {
    SCHEDULED("Programada"),
    CONFIRMED("Confirmada"),
    IN_PROGRESS("En Progreso"),
    COMPLETED("Completada"),
    CANCELLED("Cancelada");

    private final String description;

    AppointmentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean canBeModified() {
        return this == SCHEDULED || this == CONFIRMED;
    }

    public boolean canBeCancelled() {
        return this == SCHEDULED || this == CONFIRMED;
    }

    public boolean isActive() {
        return this != CANCELLED && this != COMPLETED;
    }
}