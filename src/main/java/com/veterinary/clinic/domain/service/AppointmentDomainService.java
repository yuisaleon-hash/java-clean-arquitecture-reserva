package com.veterinary.clinic.domain.service;

import com.veterinary.clinic.domain.model.Appointment;
import com.veterinary.clinic.domain.model.valueobjects.VeterinarianId;
import com.veterinary.clinic.domain.exception.AppointmentConflictException;
import com.veterinary.clinic.domain.repository.AppointmentRepository;
import com.veterinary.clinic.shared.annotation.DomainService;
import java.time.LocalDateTime;
import java.util.List;

@DomainService
public class AppointmentDomainService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentDomainService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void validateNoConflicts(Appointment appointment) {
        List<Appointment> existingAppointments = appointmentRepository
                .findByVeterinarianAndDateRange(
                        appointment.getVeterinarianId(),
                        appointment.getAppointmentDate().toLocalDate(),
                        appointment.getAppointmentDate().toLocalDate().plusDays(1)
                );

        boolean hasConflict = existingAppointments.stream()
                .filter(existing -> !existing.getId().equals(appointment.getId()))
                .filter(existing -> existing.getStatus().isActive())
                .anyMatch(existing -> existing.overlaps(appointment));

        if (hasConflict) {
            throw new AppointmentConflictException(
                    "Veterinarian already has an appointment at this time"
            );
        }
    }

    public boolean isVeterinarianAvailable(VeterinarianId veterinarianId,
                                           LocalDateTime startTime,
                                           LocalDateTime endTime) {
        List<Appointment> appointments = appointmentRepository
                .findByVeterinarianAndDateRange(
                        veterinarianId,
                        startTime.toLocalDate(),
                        endTime.toLocalDate().plusDays(1)
                );

        return appointments.stream()
                .filter(apt -> apt.getStatus().isActive())
                .noneMatch(apt ->
                        startTime.isBefore(apt.getEndTime()) &&
                                endTime.isAfter(apt.getAppointmentDate())
                );
    }
}