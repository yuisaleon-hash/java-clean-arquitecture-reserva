package com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository;

import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.ReservationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.UUID;

public interface ReservationJpaRepository
        extends JpaRepository<ReservationJpaEntity, UUID> {

    @Query("""
        SELECT COUNT(r) > 0 FROM ReservationJpaEntity r
        WHERE r.roomId = :roomId
        AND r.checkIn < :checkOut
        AND r.checkOut > :checkIn
    """)
    boolean existsConflict(UUID roomId, LocalDate checkIn, LocalDate checkOut);
}