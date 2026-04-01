package com.reserva.hotel.domain.service;

import java.time.LocalDate;

public class ReservaDomainService {

    public void validarDisponibilidad(String hotelId,
                                      LocalDate fechaIngreso,
                                      LocalDate fechaSalida) {

        if (fechaIngreso.isAfter(fechaSalida)) {
            throw new RuntimeException("La fecha de ingreso no puede ser mayor a la de salida");
        }

        // Aquí luego puedes validar contra repositorio si quieres
    }
}