package com.reserva.hotel.application.dto.query;

import com.reserva.hotel.domain.repository.ReservaRepository;
import com.reserva.hotel.domain.model.Reserva;

public class GetReservaHandler {

    private final ReservaRepository repo;

    public GetReservaHandler(ReservaRepository repo) {
        this.repo = repo;
    }

    public Reserva execute(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrada"));
    }
}