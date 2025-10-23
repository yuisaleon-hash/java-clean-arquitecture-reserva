package com.veterinary.clinic.application.dto.response;

import java.time.LocalDateTime;

public class AppointmentResponse {
    private String id;
    private String petId;
    private String petName;
    private String ownerName;
    private String veterinarianId;
    private String veterinarianName;
    private LocalDateTime appointmentDate;
    private Integer durationMinutes;
    private String reason;
    private String status;
    private String notes;

    // Constructor por defecto
    public AppointmentResponse() {}

    // Constructor completo
    public AppointmentResponse(String id, String petId, String petName, String ownerName,
                               String veterinarianId, String veterinarianName,
                               LocalDateTime appointmentDate, Integer durationMinutes,
                               String reason, String status, String notes) {
        this.id = id;
        this.petId = petId;
        this.petName = petName;
        this.ownerName = ownerName;
        this.veterinarianId = veterinarianId;
        this.veterinarianName = veterinarianName;
        this.appointmentDate = appointmentDate;
        this.durationMinutes = durationMinutes;
        this.reason = reason;
        this.status = status;
        this.notes = notes;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPetId() { return petId; }
    public void setPetId(String petId) { this.petId = petId; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getVeterinarianId() { return veterinarianId; }
    public void setVeterinarianId(String veterinarianId) { this.veterinarianId = veterinarianId; }

    public String getVeterinarianName() { return veterinarianName; }
    public void setVeterinarianName(String veterinarianName) { this.veterinarianName = veterinarianName; }

    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}