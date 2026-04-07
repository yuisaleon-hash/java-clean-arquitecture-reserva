package com.reserva.hotel.application.service;

import com.reserva.hotel.application.port.in.GetReservaUseCase;
import com.reserva.hotel.application.port.out.ReservaPersistencePort;
import com.reserva.hotel.domain.model.Reserva;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetReservaHandler implements GetReservaUseCase {

    private final ReservaPersistencePort reservaPersistence;

    @Override
    public Reserva execute(String id) {
        return reservaPersistence.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con id: " + id));
    }
}