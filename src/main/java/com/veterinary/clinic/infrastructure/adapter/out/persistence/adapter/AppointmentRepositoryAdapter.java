package com.veterinary.clinic.infrastructure.adapter.out.persistence.adapter;

import com.veterinary.clinic.domain.model.Appointment;
import com.veterinary.clinic.domain.model.valueobjects.AppointmentId;
import com.veterinary.clinic.domain.model.valueobjects.PetId;
import com.veterinary.clinic.domain.model.valueobjects.VeterinarianId;
import com.veterinary.clinic.domain.repository.AppointmentRepository;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.AppointmentJpaEntity;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository.AppointmentJpaRepository;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.mapper.AppointmentJpaMapper;
import com.veterinary.clinic.shared.annotation.Adapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class AppointmentRepositoryAdapter implements AppointmentRepository {

    private final AppointmentJpaRepository jpaRepository;
    private final AppointmentJpaMapper mapper;

    public AppointmentRepositoryAdapter(AppointmentJpaRepository jpaRepository,
                                        AppointmentJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Appointment save(Appointment appointment) {
        AppointmentJpaEntity entity = mapper.toJpaEntity(appointment);
        AppointmentJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomainModel(savedEntity);
    }

    @Override
    public Optional<Appointment> findById(AppointmentId id) {
        return jpaRepository.findById(id.getValue())
                .map(mapper::toDomainModel);
    }

    @Override
    public List<Appointment> findByPetId(PetId petId) {
        return jpaRepository.findByPetId(petId.getValue())
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByVeterinarianAndDateRange(VeterinarianId veterinarianId,
                                                            LocalDate startDate,
                                                            LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atStartOfDay();

        return jpaRepository.findByVeterinarianAndDateRange(
                        veterinarianId.getValue(), startDateTime, endDateTime)
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByDate(LocalDate date) {
        return jpaRepository.findByDate(date)
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findUpcomingAppointments(LocalDate fromDate) {
        LocalDateTime fromDateTime = fromDate.atStartOfDay();
        return jpaRepository.findUpcomingAppointments(fromDateTime)
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(AppointmentId id) {
        jpaRepository.deleteById(id.getValue());
    }
}