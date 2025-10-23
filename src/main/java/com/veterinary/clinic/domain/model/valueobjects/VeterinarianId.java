package com.veterinary.clinic.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class VeterinarianId {
    private final UUID value;

    public VeterinarianId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("VeterinarianId cannot be null");
        }
        this.value = value;
    }

    public static VeterinarianId generate() {
        return new VeterinarianId(UUID.randomUUID());
    }

    public static VeterinarianId of(String value) {
        return new VeterinarianId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VeterinarianId)) return false;
        VeterinarianId that = (VeterinarianId) o;
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