package com.veterinary.clinic.application.service;

import com.veterinary.clinic.application.port.in.ScheduleAppointmentUseCase;
import com.veterinary.clinic.application.port.in.FindAppointmentsByDateUseCase;
import com.veterinary.clinic.application.port.out.EmailNotificationPort;
import com.veterinary.clinic.application.dto.command.ScheduleAppointmentCommand;
import com.veterinary.clinic.application.dto.query.FindAppointmentsByDateQuery;
import com.veterinary.clinic.application.dto.response.AppointmentResponse;
import com.veterinary.clinic.application.mapper.AppointmentMapper;
import com.veterinary.clinic.domain.model.Appointment;
import com.veterinary.clinic.domain.model.Pet;
import com.veterinary.clinic.domain.model.Owner;
import com.veterinary.clinic.domain.model.Veterinarian;
import com.veterinary.clinic.domain.model.valueobjects.*;
import com.veterinary.clinic.domain.repository.AppointmentRepository;
import com.veterinary.clinic.domain.repository.PetRepository;
import com.veterinary.clinic.domain.repository.OwnerRepository;
import com.veterinary.clinic.domain.repository.VeterinarianRepository;
import com.veterinary.clinic.domain.service.AppointmentDomainService;
import com.veterinary.clinic.domain.exception.PetNotFoundException;
import com.veterinary.clinic.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class AppointmentApplicationService implements ScheduleAppointmentUseCase, FindAppointmentsByDateUseCase {

    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final AppointmentDomainService appointmentDomainService;
    private final EmailNotificationPort emailNotificationPort;
    private final AppointmentMapper appointmentMapper;

    public AppointmentApplicationService(AppointmentRepository appointmentRepository,
                                         PetRepository petRepository,
                                         OwnerRepository ownerRepository,
                                         VeterinarianRepository veterinarianRepository,
                                         AppointmentDomainService appointmentDomainService,
                                         EmailNotificationPort emailNotificationPort,
                                         AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
        this.veterinarianRepository = veterinarianRepository;
        this.appointmentDomainService = appointmentDomainService;
        this.emailNotificationPort = emailNotificationPort;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public AppointmentResponse schedule(ScheduleAppointmentCommand command) {
        // Validar que la mascota existe
        Pet pet = petRepository.findById(PetId.of(command.getPetId()))
                .orElseThrow(() -> new PetNotFoundException(command.getPetId()));

        // Validar que el veterinario existe
        Veterinarian veterinarian = veterinarianRepository.findById(VeterinarianId.of(command.getVeterinarianId()))
                .orElseThrow(() -> new RuntimeException("Veterinarian not found"));

        // Crear la cita
        Appointment appointment = Appointment.schedule(
                pet.getId(),
                veterinarian.getId(),
                command.getAppointmentDate(),
                Duration.ofMinutes(command.getDurationMinutes()),
                command.getReason()
        );

        // Validar que no hay conflictos
        appointmentDomainService.validateNoConflicts(appointment);

        // Guardar la cita
        Appointment savedAppointment = appointmentRepository.save(appointment);

        // Obtener el dueño para la notificación
        Owner owner = ownerRepository.findById(pet.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        // Enviar notificación por email
        emailNotificationPort.sendAppointmentConfirmation(
                owner.getEmail().getValue(),
                owner.getFullName(),
                pet.getName(),
                appointment.getAppointmentDate().toString()
        );

        // Convertir a DTO de respuesta
        return appointmentMapper.toResponse(savedAppointment, pet, owner, veterinarian);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentResponse> findByDate(FindAppointmentsByDateQuery query) {
        List<Appointment> appointments = appointmentRepository.findByDate(query.getDate());

        return appointments.stream()
                .filter(appointment -> query.getVeterinarianId() == null ||
                        appointment.getVeterinarianId().toString().equals(query.getVeterinarianId()))
                .filter(appointment -> query.getStatus() == null ||
                        appointment.getStatus().name().equals(query.getStatus()))
                .map(this::enrichAppointmentResponse)
                .collect(Collectors.toList());
    }

    private AppointmentResponse enrichAppointmentResponse(Appointment appointment) {
        Pet pet = petRepository.findById(appointment.getPetId())
                .orElseThrow(() -> new PetNotFoundException(appointment.getPetId().toString()));

        Owner owner = ownerRepository.findById(pet.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Veterinarian veterinarian = veterinarianRepository.findById(appointment.getVeterinarianId())
                .orElseThrow(() -> new RuntimeException("Veterinarian not found"));

        return appointmentMapper.toResponse(appointment, pet, owner, veterinarian);
    }
}