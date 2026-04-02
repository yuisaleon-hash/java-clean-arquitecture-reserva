package com.reserva.hotel.infrastructure.adapter.out.persistence;

import com.reserva.hotel.application.port.out.ReservaPersistencePort;
import com.reserva.hotel.domain.model.Reserva;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ReservaRepositoryAdapter implements ReservaPersistencePort {

    private final Map<String, Reserva> database = new HashMap<>();

    @Override
    public Reserva save(Reserva reserva) {
        database.put(reserva.getId(), reserva);
        return reserva;
    }

    @Override
    public Optional<Reserva> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }
}