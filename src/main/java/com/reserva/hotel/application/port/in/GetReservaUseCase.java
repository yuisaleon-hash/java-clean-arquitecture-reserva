package com.reserva.hotel.application.port.in;

import com.reserva.hotel.domain.model.Reserva;

public interface GetReservaUseCase {
    Reserva execute(String id);
}