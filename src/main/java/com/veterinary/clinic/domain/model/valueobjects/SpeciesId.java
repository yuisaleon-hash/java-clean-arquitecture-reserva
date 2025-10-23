package com.veterinary.clinic.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class SpeciesId {
    private final UUID value;

    public SpeciesId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("SpeciesId cannot be null");
        }
        this.value = value;
    }

    public static SpeciesId generate() {
        return new SpeciesId(UUID.randomUUID());
    }

    public static SpeciesId of(String value) {
        return new SpeciesId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpeciesId)) return false;
        SpeciesId speciesId = (SpeciesId) o;
        return Objects.equals(value, speciesId.value);
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