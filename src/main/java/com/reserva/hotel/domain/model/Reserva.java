package com.reserva.hotel.domain.model;

import com.reserva.hotel.domain.exception.BusinessRuleException;
import java.time.LocalDate;

public class Reserva {

    private final String id;
    private final String clienteId;
    private final String hotelId;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private EstadoReserva estado;

    public Reserva(String id, String clienteId, String hotelId,
                   LocalDate fechaInicio, LocalDate fechaFin) {
        this.id          = id;
        this.clienteId   = clienteId;
        this.hotelId     = hotelId;
        this.fechaInicio = fechaInicio;
        this.fechaFin    = fechaFin;
        this.estado      = EstadoReserva.PENDIENTE;
    }

    public void confirmar() {
        if (this.estado != EstadoReserva.PENDIENTE) {
            throw new BusinessRuleException(
                "Solo se puede confirmar una reserva en estado PENDIENTE. Estado actual: " + estado);
        }
        this.estado = EstadoReserva.CONFIRMADA;
    }

    public void cancelar() {
        if (this.estado == EstadoReserva.COMPLETADA) {
            throw new BusinessRuleException(
                "No se puede cancelar una reserva ya completada");
        }
        if (this.estado == EstadoReserva.CANCELADA) {
            throw new BusinessRuleException(
                "La reserva ya se encuentra cancelada");
        }
        this.estado = EstadoReserva.CANCELADA;
    }

    public void completar() {
        if (this.estado != EstadoReserva.CONFIRMADA) {
            throw new BusinessRuleException(
                "Solo se puede completar una reserva en estado CONFIRMADA. Estado actual: " + estado);
        }
        this.estado = EstadoReserva.COMPLETADA;
    }

    public String getId()             { return id; }
    public String getClienteId()      { return clienteId; }
    public String getHotelId()        { return hotelId; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin()    { return fechaFin; }
    public EstadoReserva getEstado()  { return estado; }
}
