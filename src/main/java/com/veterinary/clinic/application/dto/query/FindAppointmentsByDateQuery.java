package com.veterinary.clinic.application.dto.query;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class FindAppointmentsByDateQuery {

    @NotNull(message = "Date is required")
    private LocalDate date;

    private String veterinarianId;

    private String status;

    public FindAppointmentsByDateQuery() {}

    public FindAppointmentsByDateQuery(LocalDate date, String veterinarianId, String status) {
        this.date = date;
        this.veterinarianId = veterinarianId;
        this.status = status;
    }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getVeterinarianId() { return veterinarianId; }
    public void setVeterinarianId(String veterinarianId) { this.veterinarianId = veterinarianId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}