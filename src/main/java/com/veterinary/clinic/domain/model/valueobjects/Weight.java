package com.veterinary.clinic.domain.model.valueobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;  // Cambiar import
import java.util.Objects;

public class Weight {
    private static final BigDecimal MIN_WEIGHT = BigDecimal.ZERO;
    private static final BigDecimal MAX_WEIGHT = new BigDecimal("1000.00");

    private final BigDecimal value;

    public Weight(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }
        if (value.compareTo(MIN_WEIGHT) <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        if (value.compareTo(MAX_WEIGHT) > 0) {
            throw new IllegalArgumentException("Weight cannot exceed " + MAX_WEIGHT + " kg");
        }
        // Cambiar BigDecimal.ROUND_HALF_UP por RoundingMode.HALF_UP
        this.value = value.setScale(2, RoundingMode.HALF_UP);
        //this.value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static Weight of(double value) {
        return new Weight(BigDecimal.valueOf(value));
    }

    public BigDecimal getValue() {
        return value;
    }

    public boolean isGreaterThan(Weight other) {
        return this.value.compareTo(other.value) > 0;
    }

    public boolean isLessThan(Weight other) {
        return this.value.compareTo(other.value) < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weight)) return false;
        Weight weight = (Weight) o;
        return Objects.equals(value, weight.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + " kg";
    }
}