package com.veterinary.clinic.application.port.out;

import com.veterinary.clinic.domain.model.Reservation;

import java.time.LocalDate;
import java.util.UUID;

public interface ReservaPersistencePort {

    Reserva save(Reserva reserva);

    boolean existsConflict(UUID roomId, LocalDate checkIn, LocalDate checkOut);
}