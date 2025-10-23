package com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository;

import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.AppointmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentJpaEntity, UUID> {

    List<AppointmentJpaEntity> findByPetId(UUID petId);

    @Query("SELECT a FROM AppointmentJpaEntity a WHERE a.veterinarianId = :veterinarianId " +
            "AND a.appointmentDate >= :startDate AND a.appointmentDate < :endDate")
    List<AppointmentJpaEntity> findByVeterinarianAndDateRange(
            @Param("veterinarianId") UUID veterinarianId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT a FROM AppointmentJpaEntity a WHERE DATE(a.appointmentDate) = :date")
    List<AppointmentJpaEntity> findByDate(@Param("date") LocalDate date);

    @Query("SELECT a FROM AppointmentJpaEntity a WHERE a.appointmentDate >= :fromDate " +
            "AND a.status IN ('SCHEDULED', 'CONFIRMED') ORDER BY a.appointmentDate")
    List<AppointmentJpaEntity> findUpcomingAppointments(@Param("fromDate") LocalDateTime fromDate);
}