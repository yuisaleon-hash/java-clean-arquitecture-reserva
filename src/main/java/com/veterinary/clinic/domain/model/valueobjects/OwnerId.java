package com.veterinary.clinic.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class OwnerId {
    private final UUID value;

    public OwnerId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("OwnerId cannot be null");
        }
        this.value = value;
    }

    public static OwnerId generate() {
        return new OwnerId(UUID.randomUUID());
    }

    public static OwnerId of(String value) {
        return new OwnerId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnerId)) return false;
        OwnerId ownerId = (OwnerId) o;
        return Objects.equals(value, ownerId.value);
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