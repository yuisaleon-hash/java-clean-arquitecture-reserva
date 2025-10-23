package com.veterinary.clinic.infrastructure.adapter.out.persistence.mapper;

import com.veterinary.clinic.domain.model.Appointment;
import com.veterinary.clinic.domain.model.valueobjects.*;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.AppointmentJpaEntity;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class AppointmentJpaMapper {

    public AppointmentJpaEntity toJpaEntity(Appointment appointment) {
        AppointmentJpaEntity entity = new AppointmentJpaEntity();
        entity.setId(appointment.getId().getValue());
        entity.setPetId(appointment.getPetId().getValue());
        entity.setVeterinarianId(appointment.getVeterinarianId().getValue());
        entity.setAppointmentDate(appointment.getAppointmentDate());
        entity.setDurationMinutes((int) appointment.getDuration().toMinutes());
        entity.setReason(appointment.getReason());
        entity.setStatus(AppointmentJpaEntity.AppointmentStatusJpa.valueOf(appointment.getStatus().name()));
        entity.setNotes(appointment.getNotes());
        return entity;
    }

    public Appointment toDomainModel(AppointmentJpaEntity entity) {
        // Necesitaríamos también cargar Pet, Owner y Veterinarian para crear el objeto completo
        // Por simplicidad, creamos solo con los IDs
        Appointment appointment = new Appointment(
                new AppointmentId(entity.getId()),
                new PetId(entity.getPetId()),
                new VeterinarianId(entity.getVeterinarianId()),
                entity.getAppointmentDate(),
                Duration.ofMinutes(entity.getDurationMinutes()),
                entity.getReason()
        );

        // Establecer el estado y notas si es necesario
        // appointment.setStatus(...) - necesitaríamos métodos adicionales en el dominio

        return appointment;
    }
}