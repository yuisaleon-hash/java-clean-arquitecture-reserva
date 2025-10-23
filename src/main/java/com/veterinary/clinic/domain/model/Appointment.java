package com.veterinary.clinic.domain.model;

import com.veterinary.clinic.domain.model.valueobjects.*;
import com.veterinary.clinic.domain.exception.InvalidAppointmentException;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Objects;

public class Appointment {
    private final AppointmentId id;
    private final PetId petId;
    private final VeterinarianId veterinarianId;
    private LocalDateTime appointmentDate;
    private Duration duration;
    private String reason;
    private AppointmentStatus status;
    private String notes;

    public Appointment(AppointmentId id, PetId petId, VeterinarianId veterinarianId,
                       LocalDateTime appointmentDate, Duration duration, String reason) {
        this.id = Objects.requireNonNull(id, "Appointment ID cannot be null");
        this.petId = Objects.requireNonNull(petId, "Pet ID cannot be null");
        this.veterinarianId = Objects.requireNonNull(veterinarianId, "Veterinarian ID cannot be null");
        this.setAppointmentDate(appointmentDate);
        this.setDuration(duration);
        this.setReason(reason);
        this.status = AppointmentStatus.SCHEDULED;
    }

    public static Appointment schedule(PetId petId, VeterinarianId veterinarianId,
                                       LocalDateTime appointmentDate, Duration duration, String reason) {
        return new Appointment(AppointmentId.generate(), petId, veterinarianId,
                appointmentDate, duration, reason);
    }

    public void confirm() {
        if (status != AppointmentStatus.SCHEDULED) {
            throw new InvalidAppointmentException("Only scheduled appointments can be confirmed");
        }
        this.status = AppointmentStatus.CONFIRMED;
    }

    public void start() {
        if (status != AppointmentStatus.CONFIRMED && status != AppointmentStatus.SCHEDULED) {
            throw new InvalidAppointmentException("Cannot start appointment with status: " + status);
        }
        this.status = AppointmentStatus.IN_PROGRESS;
    }

    public void complete() {
        if (status != AppointmentStatus.IN_PROGRESS) {
            throw new InvalidAppointmentException("Only in-progress appointments can be completed");
        }
        this.status = AppointmentStatus.COMPLETED;
    }

    public void cancel() {
        if (!status.canBeCancelled()) {
            throw new InvalidAppointmentException("Cannot cancel appointment with status: " + status);
        }
        this.status = AppointmentStatus.CANCELLED;
    }

    public void reschedule(LocalDateTime newDate) {
        if (!status.canBeModified()) {
            throw new InvalidAppointmentException("Cannot reschedule appointment with status: " + status);
        }
        setAppointmentDate(newDate);
    }

    public void addNotes(String notes) {
        this.notes = notes;
    }

    private void setAppointmentDate(LocalDateTime appointmentDate) {
        if (appointmentDate == null) {
            throw new IllegalArgumentException("Appointment date cannot be null");
        }
        if (appointmentDate.isBefore(LocalDateTime.now())) {
            throw new InvalidAppointmentException("Appointment date cannot be in the past");
        }
        this.appointmentDate = appointmentDate;
    }

    private void setDuration(Duration duration) {
        if (duration == null || duration.isNegative() || duration.isZero()) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        if (duration.toMinutes() > 480) { // 8 hours max
            throw new IllegalArgumentException("Appointment duration cannot exceed 8 hours");
        }
        this.duration = duration;
    }

    private void setReason(String reason) {
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment reason cannot be null or empty");
        }
        this.reason = reason.trim();
    }

    public LocalDateTime getEndTime() {
        return appointmentDate.plus(duration);
    }

    public boolean overlaps(Appointment other) {
        if (!this.veterinarianId.equals(other.veterinarianId)) {
            return false;
        }

        LocalDateTime thisStart = this.appointmentDate;
        LocalDateTime thisEnd = this.getEndTime();
        LocalDateTime otherStart = other.appointmentDate;
        LocalDateTime otherEnd = other.getEndTime();

        return thisStart.isBefore(otherEnd) && otherStart.isBefore(thisEnd);
    }

    // Getters
    public AppointmentId getId() { return id; }
    public PetId getPetId() { return petId; }
    public VeterinarianId getVeterinarianId() { return veterinarianId; }
    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public Duration getDuration() { return duration; }
    public String getReason() { return reason; }
    public AppointmentStatus getStatus() { return status; }
    public String getNotes() { return notes; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}