package com.reserva.hotel.application.dto.response;

import com.reserva.hotel.domain.model.EstadoReserva;

import java.time.LocalDate;

public class ReservaResponse {

    private String id;
    private String clienteId;
    private String hotelId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoReserva estado;
    private String mensaje;

    public ReservaResponse(String id, String clienteId, String hotelId,
                           LocalDate fechaInicio, LocalDate fechaFin,
                           EstadoReserva estado, String mensaje) {
        this.id = id;
        this.clienteId = clienteId;
        this.hotelId = hotelId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.mensaje = mensaje;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public String getMensaje() {
        return mensaje;
    }
}