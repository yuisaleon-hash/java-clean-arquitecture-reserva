package com.reserva.hotel.application.port.out;

import com.reserva.hotel.domain.model.Reserva;
import java.util.Optional;

public interface ReservaPersistencePort {

    Reserva save(Reserva reserva);

    Optional<Reserva> findById(String id);
}