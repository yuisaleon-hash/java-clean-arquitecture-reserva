package com.veterinary.clinic.domain.model.valueobjects;

import com.veterinary.clinic.domain.model.Appointment;
import com.veterinary.clinic.domain.exception.InvalidAppointmentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    @Test
    @DisplayName("Debe crear una cita válida")
    void shouldCreateValidAppointment() {
        // Given
        AppointmentId id = new AppointmentId(UUID.randomUUID());
        PetId petId = new PetId(UUID.randomUUID());
        VeterinarianId veterinarianId = new VeterinarianId(UUID.randomUUID());
        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
        Duration duration = Duration.ofMinutes(30);
        String reason = "Consulta general";

        // When
        Appointment appointment = new Appointment(id, petId, veterinarianId, futureDate, duration, reason);

        // Then
        assertNotNull(appointment);
        assertEquals(id, appointment.getId());
        assertEquals(petId, appointment.getPetId());
        assertEquals(veterinarianId, appointment.getVeterinarianId());
        assertEquals(futureDate, appointment.getAppointmentDate());
        assertEquals(duration, appointment.getDuration());
        assertEquals(reason, appointment.getReason());
        assertEquals(AppointmentStatus.SCHEDULED, appointment.getStatus());
    }

    @Test
    @DisplayName("No debe permitir crear cita con fecha en el pasado")
    void shouldNotCreateAppointmentWithPastDate() {
        // Given
        AppointmentId id = new AppointmentId(UUID.randomUUID());
        PetId petId = new PetId(UUID.randomUUID());
        VeterinarianId veterinarianId = new VeterinarianId(UUID.randomUUID());
        LocalDateTime pastDate = LocalDateTime.now().minusDays(1);
        Duration duration = Duration.ofMinutes(30);
        String reason = "Consulta general";

        // When & Then
        assertThrows(InvalidAppointmentException.class, () -> {
            new Appointment(id, petId, veterinarianId, pastDate, duration, reason);
        });
    }

    @Test
    @DisplayName("Debe confirmar una cita programada")
    void shouldConfirmScheduledAppointment() {
        // Given
        Appointment appointment = createValidAppointment();

        // When
        appointment.confirm();

        // Then
        assertEquals(AppointmentStatus.CONFIRMED, appointment.getStatus());
    }

    @Test
    @DisplayName("No debe confirmar una cita que no está programada")
    void shouldNotConfirmNonScheduledAppointment() {
        // Given
        Appointment appointment = createValidAppointment();
        appointment.confirm();
        appointment.start();

        // When & Then
        assertThrows(InvalidAppointmentException.class, appointment::confirm);
    }

    @Test
    @DisplayName("Debe detectar solapamiento entre citas")
    void shouldDetectOverlappingAppointments() {
        // Given
        VeterinarianId veterinarianId = new VeterinarianId(UUID.randomUUID());
        LocalDateTime baseTime = LocalDateTime.now().plusDays(1);

        Appointment appointment1 = Appointment.schedule(
                new PetId(UUID.randomUUID()),
                veterinarianId,
                baseTime,
                Duration.ofMinutes(60),
                "Consulta 1"
        );

        Appointment appointment2 = Appointment.schedule(
                new PetId(UUID.randomUUID()),
                veterinarianId,
                baseTime.plusMinutes(30),
                Duration.ofMinutes(60),
                "Consulta 2"
        );

        // When
        boolean overlaps = appointment1.overlaps(appointment2);

        // Then
        assertTrue(overlaps);
    }

    private Appointment createValidAppointment() {
        return Appointment.schedule(
                new PetId(UUID.randomUUID()),
                new VeterinarianId(UUID.randomUUID()),
                LocalDateTime.now().plusDays(1),
                Duration.ofMinutes(30),
                "Consulta general"
        );
    }
}