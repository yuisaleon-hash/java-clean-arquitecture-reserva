package com.reserva.hotel.infrastructure.adapter.out.persistence;

import com.reserva.hotel.application.port.out.ReservaPersistencePort;
import com.reserva.hotel.domain.model.Reserva;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository // 🔥 CLAVE
public class ReservaRepositoryAdapter implements ReservaPersistencePort {

    private final Map<String, Reserva> database = new HashMap<>();

    @Override
    public void save(Reserva reserva) {
        database.put(reserva.getId(), reserva);
    }

    @Override
    public Reserva findById(String id) {
        return database.get(id);
    }
}