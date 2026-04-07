package com.reserva.hotel.domain.service;

import com.reserva.hotel.domain.model.Hotel;
import com.reserva.hotel.domain.model.Reserva;
import com.reserva.hotel.domain.exception.RoomUnavailableException;

import org.springframework.stereotype.Service; // ✅ IMPORTANTE

@Service // ✅ ESTA LÍNEA SOLUCIONA TODO
public class ReservaDisponibilidadService {

    public void validarDisponibilidad(Hotel hotel, Reserva reserva) {
        if (!hotel.tieneDisponibilidad()) {
            throw new RoomUnavailableException(
                    hotel.getId(),
                    reserva.getFechaInicio().toString(),
                    reserva.getFechaFin().toString()
            );
        }
    }
}