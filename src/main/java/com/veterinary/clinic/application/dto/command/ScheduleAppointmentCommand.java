package com.veterinary.clinic.application.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public class ScheduleAppointmentCommand {

    @NotNull(message = "Pet ID is required")
    private String petId;

    @NotNull(message = "Veterinarian ID is required")
    private String veterinarianId;

    @NotNull(message = "Appointment date is required")
    @Future(message = "Appointment date must be in the future")
    private LocalDateTime appointmentDate;

    @Positive(message = "Duration must be positive")
    private Integer durationMinutes;

    @NotBlank(message = "Reason is required")
    private String reason;

    // Constructor por defecto
    public ScheduleAppointmentCommand() {}

    public ScheduleAppointmentCommand(String petId, String veterinarianId,
                                      LocalDateTime appointmentDate,
                                      Integer durationMinutes, String reason) {
        this.petId = petId;
        this.veterinarianId = veterinarianId;
        this.appointmentDate = appointmentDate;
        this.durationMinutes = durationMinutes;
        this.reason = reason;
    }

    // Getters y Setters
    public String getPetId() { return petId; }
    public void setPetId(String petId) { this.petId = petId; }

    public String getVeterinarianId() { return veterinarianId; }
    public void setVeterinarianId(String veterinarianId) { this.veterinarianId = veterinarianId; }

    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}