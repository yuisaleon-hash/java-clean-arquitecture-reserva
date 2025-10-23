package com.veterinary.clinic.domain.model.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WeightTest {

    @Test
    @DisplayName("Debe crear peso válido")
    void shouldCreateValidWeight() {
        // Given
        BigDecimal value = new BigDecimal("5.50");

        // When
        Weight weight = new Weight(value);

        // Then
        assertEquals(new BigDecimal("5.50"), weight.getValue());
    }

    @Test
    @DisplayName("No debe permitir peso negativo")
    void shouldNotAllowNegativeWeight() {
        // Given
        BigDecimal negativeValue = new BigDecimal("-1.0");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Weight(negativeValue));
    }

    @Test
    @DisplayName("No debe permitir peso cero")
    void shouldNotAllowZeroWeight() {
        // Given
        BigDecimal zeroValue = BigDecimal.ZERO;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Weight(zeroValue));
    }

    @Test
    @DisplayName("Debe comparar pesos correctamente")
    void shouldCompareWeightsCorrectly() {
        // Given
        Weight weight1 = Weight.of(5.5);
        Weight weight2 = Weight.of(3.2);

        // When & Then
        assertTrue(weight1.isGreaterThan(weight2));
        assertTrue(weight2.isLessThan(weight1));
        assertFalse(weight1.isLessThan(weight2));
    }
}