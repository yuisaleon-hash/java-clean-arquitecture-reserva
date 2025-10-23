package com.veterinary.clinic.application.service;

import com.veterinary.clinic.application.dto.command.ScheduleAppointmentCommand;
import com.veterinary.clinic.application.dto.response.AppointmentResponse;
import com.veterinary.clinic.application.mapper.AppointmentMapper;
import com.veterinary.clinic.application.port.out.EmailNotificationPort;
import com.veterinary.clinic.domain.model.*;
import com.veterinary.clinic.domain.model.valueobjects.*;
import com.veterinary.clinic.domain.repository.*;
import com.veterinary.clinic.domain.service.AppointmentDomainService;
import com.veterinary.clinic.domain.exception.PetNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentApplicationServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private VeterinarianRepository veterinarianRepository;

    @Mock
    private AppointmentDomainService appointmentDomainService;

    @Mock
    private EmailNotificationPort emailNotificationPort;

    @Mock
    private AppointmentMapper appointmentMapper;

    private AppointmentApplicationService service;

    @BeforeEach
    void setUp() {
        service = new AppointmentApplicationService(
                appointmentRepository,
                petRepository,
                ownerRepository,
                veterinarianRepository,
                appointmentDomainService,
                emailNotificationPort,
                appointmentMapper
        );
    }

    @Test
    @DisplayName("Debe programar cita exitosamente")
    void shouldScheduleAppointmentSuccessfully() {
        // Given
        ScheduleAppointmentCommand command = new ScheduleAppointmentCommand(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusDays(1),
                30,
                "Consulta general"
        );

        Pet mockPet = mock(Pet.class);
        Veterinarian mockVeterinarian = mock(Veterinarian.class);
        Owner mockOwner = mock(Owner.class);
        Appointment mockAppointment = mock(Appointment.class);
        AppointmentResponse mockResponse = new AppointmentResponse();

        when(petRepository.findById(any(PetId.class))).thenReturn(Optional.of(mockPet));
        when(veterinarianRepository.findById(any(VeterinarianId.class))).thenReturn(Optional.of(mockVeterinarian));
        when(ownerRepository.findById(any(OwnerId.class))).thenReturn(Optional.of(mockOwner));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(mockAppointment);
        when(appointmentMapper.toResponse(any(), any(), any(), any())).thenReturn(mockResponse);
        when(mockPet.getOwnerId()).thenReturn(new OwnerId(UUID.randomUUID()));
        when(mockPet.getId()).thenReturn(new PetId(UUID.randomUUID()));
        when(mockVeterinarian.getId()).thenReturn(new VeterinarianId(UUID.randomUUID()));
        when(mockOwner.getEmail()).thenReturn(new Email("test@example.com"));
        when(mockOwner.getFullName()).thenReturn("John Doe");
        when(mockPet.getName()).thenReturn("Buddy");

        // When
        AppointmentResponse result = service.schedule(command);

        // Then
        assertNotNull(result);
        verify(appointmentDomainService).validateNoConflicts(any(Appointment.class));
        verify(appointmentRepository).save(any(Appointment.class));
        verify(emailNotificationPort).sendAppointmentConfirmation(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Debe fallar cuando la mascota no existe")
    void shouldFailWhenPetNotFound() {
        // Given
        ScheduleAppointmentCommand command = new ScheduleAppointmentCommand(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusDays(1),
                30,
                "Consulta general"
        );

        when(petRepository.findById(any(PetId.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(PetNotFoundException.class, () -> service.schedule(command));
        verify(appointmentRepository, never()).save(any());
        verify(emailNotificationPort, never()).sendAppointmentConfirmation(anyString(), anyString(), anyString(), anyString());
    }
}