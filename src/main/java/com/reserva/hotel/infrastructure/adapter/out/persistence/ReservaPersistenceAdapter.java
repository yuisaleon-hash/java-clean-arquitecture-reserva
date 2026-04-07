package com.reserva.hotel.infrastructure.adapter.out.persistence;

import com.reserva.hotel.application.port.out.ReservaPersistencePort;
import com.reserva.hotel.domain.model.Reserva;
import com.reserva.hotel.domain.repository.ReservaRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReservaPersistenceAdapter implements ReservaPersistencePort {

    private final ReservaRepository repository;

    public ReservaPersistenceAdapter(ReservaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Reserva> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Reserva save(Reserva reserva) {
        return repository.save(reserva);
    }
}