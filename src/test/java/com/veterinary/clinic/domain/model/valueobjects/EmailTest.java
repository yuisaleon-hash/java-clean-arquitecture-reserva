package com.veterinary.clinic.domain.model.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    @DisplayName("Debe crear email válido")
    void shouldCreateValidEmail() {
        // Given
        String validEmail = "test@example.com";

        // When
        Email email = new Email(validEmail);

        // Then
        assertEquals(validEmail, email.getValue());
    }

    @Test
    @DisplayName("Debe normalizar email a minúsculas")
    void shouldNormalizeEmailToLowercase() {
        // Given
        String mixedCaseEmail = "Test@EXAMPLE.COM";

        // When
        Email email = new Email(mixedCaseEmail);

        // Then
        assertEquals("test@example.com", email.getValue());
    }

    @Test
    @DisplayName("No debe permitir email inválido")
    void shouldNotAllowInvalidEmail() {
        // Given
        String invalidEmail = "invalid-email";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    @DisplayName("No debe permitir email nulo")
    void shouldNotAllowNullEmail() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Email(null));
    }

    @Test
    @DisplayName("No debe permitir email vacío")
    void shouldNotAllowEmptyEmail() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Email(""));
    }
}