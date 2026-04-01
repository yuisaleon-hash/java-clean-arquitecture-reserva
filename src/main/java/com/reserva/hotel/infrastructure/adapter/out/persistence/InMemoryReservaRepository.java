package com.reserva.hotel.infrastructure.adapter.out.persistence;

import com.reserva.hotel.domain.model.Reserva;
import com.reserva.hotel.domain.repository.ReservaRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryReservaRepository implements ReservaRepository {

    private final Map<String, Reserva> reservas = new HashMap<>();

    @Override
    public void save(Reserva reserva) {
        reservas.put(reserva.getId(), reserva);
    }

    @Override
    public Optional<Reserva> findById(String id) {
        return Optional.ofNullable(reservas.get(id));
    }
}