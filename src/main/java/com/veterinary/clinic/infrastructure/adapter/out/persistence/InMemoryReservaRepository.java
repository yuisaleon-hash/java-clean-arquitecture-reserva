package com.veterinary.clinic.infrastructure.adapter.out.persistence;

import com.example.hotel.domain.model.Reserva;
import com.example.hotel.domain.repository.ReservaRepository;

import java.util.*;

public class InMemoryReservaRepository implements ReservaRepository {

    private Map<String, Reserva> db = new HashMap<>();

    public void save(Reserva reserva) {
        db.put(reserva.getId(), reserva);
    }

    public Optional<Reserva> findById(String id) {
        return Optional.ofNullable(db.get(id));
    }
}