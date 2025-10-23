package com.veterinary.clinic.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class AppointmentId {
    private final UUID value;

    public AppointmentId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("AppointmentId cannot be null");
        }
        this.value = value;
    }

    public static AppointmentId generate() {
        return new AppointmentId(UUID.randomUUID());
    }

    public static AppointmentId of(String value) {
        return new AppointmentId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppointmentId)) return false;
        AppointmentId that = (AppointmentId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}