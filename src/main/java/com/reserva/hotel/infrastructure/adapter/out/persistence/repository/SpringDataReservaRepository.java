package com.reserva.hotel.infrastructure.adapter.out.persistence.repository;

import com.reserva.hotel.infrastructure.adapter.out.persistence.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataReservaRepository extends JpaRepository<ReservaEntity, String> {
}