package com.reserva.hotel.application.port.out;

import com.reserva.hotel.domain.model.Reserva;
import java.util.Optional;

public interface ReservaPersistencePort {

    Optional<Reserva> findById(String id);

    Reserva save(Reserva reserva);
}