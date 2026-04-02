package com.reserva.hotel.application.dto.response;

import com.reserva.hotel.domain.model.EstadoReserva;

public class ReservaResponse {

    private String id;
    private EstadoReserva estado;

    public ReservaResponse(String id, EstadoReserva estado) {
        this.id = id;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public EstadoReserva getEstado() {
        return estado;
    }
}