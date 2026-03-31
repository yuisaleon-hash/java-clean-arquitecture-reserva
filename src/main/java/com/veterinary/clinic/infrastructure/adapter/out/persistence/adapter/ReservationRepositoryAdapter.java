package com.veterinary.clinic.infrastructure.adapter.out.persistence.adapter;

import com.veterinary.clinic.application.port.out.ReservationPersistencePort;
import com.veterinary.clinic.domain.model.Reservation;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.mapper.ReservationJpaMapper;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository.ReservationJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReservationRepositoryAdapter implements ReservationPersistencePort {

    private final ReservationJpaRepository repository;
    private final ReservationJpaMapper mapper;

    @Override
    public Reservation save(Reservation reservation) {
        return mapper.toDomain(
                repository.save(mapper.toEntity(reservation))
        );
    }

    @Override
    public boolean existsConflict(UUID roomId, LocalDate checkIn, LocalDate checkOut) {
        return repository.existsConflict(roomId, checkIn, checkOut);
    }
}