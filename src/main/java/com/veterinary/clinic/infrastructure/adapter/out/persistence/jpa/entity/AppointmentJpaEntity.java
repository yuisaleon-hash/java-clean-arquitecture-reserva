package com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class AppointmentJpaEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "pet_id", nullable = false)
    private UUID petId;

    @Column(name = "veterinarian_id", nullable = false)
    private UUID veterinarianId;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(name = "reason", nullable = false, length = 500)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AppointmentStatusJpa status;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructores
    public AppointmentJpaEntity() {}

    public AppointmentJpaEntity(UUID id, UUID petId, UUID veterinarianId,
                                LocalDateTime appointmentDate, Integer durationMinutes,
                                String reason, AppointmentStatusJpa status, String notes) {
        this.id = id;
        this.petId = petId;
        this.veterinarianId = veterinarianId;
        this.appointmentDate = appointmentDate;
        this.durationMinutes = durationMinutes;
        this.reason = reason;
        this.status = status;
        this.notes = notes;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getPetId() { return petId; }
    public void setPetId(UUID petId) { this.petId = petId; }

    public UUID getVeterinarianId() { return veterinarianId; }
    public void setVeterinarianId(UUID veterinarianId) { this.veterinarianId = veterinarianId; }

    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public AppointmentStatusJpa getStatus() { return status; }
    public void setStatus(AppointmentStatusJpa status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public enum AppointmentStatusJpa {
        SCHEDULED, CONFIRMED, IN_PROGRESS, COMPLETED, CANCELLED
    }
}