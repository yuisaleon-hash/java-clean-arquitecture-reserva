package com.example.hotel.domain.repository;

import com.example.hotel.domain.model.Reserva;

import java.util.Optional;

public interface ReservaRepository {
    void save(Reserva reserva);
    Optional<Reserva> findById(String id);
}