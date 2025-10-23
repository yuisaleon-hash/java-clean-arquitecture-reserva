package com.veterinary.clinic.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class PetId {
    private final UUID value;

    public PetId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("PetId cannot be null");
        }
        this.value = value;
    }

    public static PetId generate() {
        return new PetId(UUID.randomUUID());
    }

    public static PetId of(String value) {
        return new PetId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetId)) return false;
        PetId petId = (PetId) o;
        return Objects.equals(value, petId.value);
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