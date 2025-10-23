package com.veterinary.clinic.application.mapper;

import com.veterinary.clinic.application.dto.response.AppointmentResponse;
import com.veterinary.clinic.domain.model.Appointment;
import com.veterinary.clinic.domain.model.Pet;
import com.veterinary.clinic.domain.model.Owner;
import com.veterinary.clinic.domain.model.Veterinarian;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentResponse toResponse(Appointment appointment, Pet pet,
                                          Owner owner, Veterinarian veterinarian) {
        AppointmentResponse response = new AppointmentResponse();
        response.setId(appointment.getId().toString());
        response.setPetId(pet.getId().toString());
        response.setPetName(pet.getName());
        response.setOwnerName(owner.getFullName());
        response.setVeterinarianId(veterinarian.getId().toString());
        response.setVeterinarianName(veterinarian.getFullName());
        response.setAppointmentDate(appointment.getAppointmentDate());
        response.setDurationMinutes((int) appointment.getDuration().toMinutes());
        response.setReason(appointment.getReason());
        response.setStatus(appointment.getStatus().name());
        response.setNotes(appointment.getNotes());
        return response;
    }
}