package com.example.hotel.domain.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {

    private String id;
    private String clienteId;
    private String hotelId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precio;
    private Estado estado;

    public enum Estado {
        ACTIVA, CANCELADA
    }

    public Reserva(String id, String clienteId, String hotelId,
                   LocalDate fechaInicio, LocalDate fechaFin, double precio) {
        this.id = id;
        this.clienteId = clienteId;
        this.hotelId = hotelId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.estado = Estado.ACTIVA;
    }

    public void cancelar(LocalDate hoy) {
        long dias = ChronoUnit.DAYS.between(hoy, fechaInicio);

        if (dias < 2) {
            throw new RuntimeException("No se puede cancelar con menos de 48h");
        }

        this.estado = Estado.CANCELADA;
    }

    public String getId() {
        return id;
    }
}