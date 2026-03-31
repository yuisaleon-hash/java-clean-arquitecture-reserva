package com.example.hotel.application.handler;

import com.example.hotel.domain.repository.ReservaRepository;
import com.example.hotel.domain.model.Reserva;

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