package com.reserva.hotel.application.port.out;

import com.reserva.hotel.domain.model.Reserva;

public interface ReservaPersistencePort {

    void save(Reserva reserva);

    Reserva findById(String id);
}