package com.example.hotel.domain.service;

import com.example.hotel.domain.model.Hotel;
import com.example.hotel.domain.model.Reserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class ReservaService {

    public double calcularPrecio(long dias) {
        double precio = dias * 100;

        if (dias >= 7) {
            precio *= 0.9;
        }

        return precio;
    }

    public Reserva crearReserva(Hotel hotel, String clienteId,
                                LocalDate inicio, LocalDate fin) {

        if (!hotel.tieneDisponibilidad()) {
            throw new RuntimeException("Hotel sin disponibilidad");
        }

        long dias = ChronoUnit.DAYS.between(inicio, fin);

        double precio = calcularPrecio(dias);

        hotel.reservarHabitacion();

        return new Reserva(
                UUID.randomUUID().toString(),
                clienteId,
                hotel.getId(),
                inicio,
                fin,
                precio
        );
    }
}