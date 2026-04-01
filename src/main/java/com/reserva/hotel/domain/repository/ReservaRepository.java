package com.reserva.hotel.domain.repository;

import com.reserva.hotel.domain.model.Reserva;

import java.util.Optional;

public interface ReservaRepository {

    void save(Reserva reserva);

    Optional<Reserva> findById(String id);
}