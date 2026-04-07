package com.reserva.hotel.infrastructure.adapter.out.persistence.repository;

import com.reserva.hotel.infrastructure.adapter.out.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, String> {
}