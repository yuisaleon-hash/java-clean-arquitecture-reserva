package com.veterinary.clinic.domain.repository;

import com.veterinary.clinic.domain.model.Appointment;
import com.veterinary.clinic.domain.model.valueobjects.AppointmentId;
import com.veterinary.clinic.domain.model.valueobjects.PetId;
import com.veterinary.clinic.domain.model.valueobjects.VeterinarianId;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {

    Appointment save(Appointment appointment);

    Optional<Appointment> findById(AppointmentId id);

    List<Appointment> findByPetId(PetId petId);

    List<Appointment> findByVeterinarianAndDateRange(VeterinarianId veterinarianId,
                                                     LocalDate startDate,
                                                     LocalDate endDate);

    List<Appointment> findByDate(LocalDate date);

    List<Appointment> findUpcomingAppointments(LocalDate fromDate);

    void delete(AppointmentId id);
}