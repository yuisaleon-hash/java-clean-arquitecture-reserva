package com.reserva.hotel.infrastructure.adapter.out.persistence.repository;

import com.reserva.hotel.infrastructure.adapter.out.persistence.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataHotelRepository extends JpaRepository<HotelEntity, String> {
}