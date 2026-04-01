package com.reserva.hotel.domain.model;

import java.time.LocalDate;

public class Reserva {

    private String id;
    private String clienteId;
    private String hotelId;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private EstadoReserva estado;

    public Reserva(String id, String clienteId, String hotelId,
                   LocalDate fechaIngreso, LocalDate fechaSalida) {

        this.id = id;
        this.clienteId = clienteId;
        this.hotelId = hotelId;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.estado = EstadoReserva.CREADA;
    }

    public String getId() {
        return id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public EstadoReserva getEstado() {
        return estado;
    }
}