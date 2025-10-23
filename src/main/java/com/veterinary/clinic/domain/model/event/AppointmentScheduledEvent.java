package com.veterinary.clinic.domain.model.event;

import com.veterinary.clinic.domain.model.valueobjects.AppointmentId;
import com.veterinary.clinic.domain.model.valueobjects.PetId;
import com.veterinary.clinic.domain.model.valueobjects.VeterinarianId;
import java.time.LocalDateTime;

public class AppointmentScheduledEvent extends DomainEvent {
    private final AppointmentId appointmentId;
    private final PetId petId;
    private final VeterinarianId veterinarianId;
    private final LocalDateTime appointmentDate;

    public AppointmentScheduledEvent(AppointmentId appointmentId, PetId petId,
                                     VeterinarianId veterinarianId, LocalDateTime appointmentDate) {
        super();
        this.appointmentId = appointmentId;
        this.petId = petId;
        this.veterinarianId = veterinarianId;
        this.appointmentDate = appointmentDate;
    }

    public AppointmentId getAppointmentId() { return appointmentId; }
    public PetId getPetId() { return petId; }
    public VeterinarianId getVeterinarianId() { return veterinarianId; }
    public LocalDateTime getAppointmentDate() { return appointmentDate; }
}