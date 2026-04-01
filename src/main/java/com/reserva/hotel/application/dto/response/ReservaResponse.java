package com.reserva.hotel.application.dto.response;

public class ReservaResponse {

    public String id;
    public String estado;

    public ReservaResponse(String id, String estado) {
        this.id = id;
        this.estado = estado;
    }
}