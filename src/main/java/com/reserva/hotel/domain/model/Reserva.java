package com.reserva.hotel.domain.model;

import com.reserva.hotel.domain.exception.BusinessRuleException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {                           // ← faltaba esto

    private final String id;
    private final String clienteId;
    private final String hotelId;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private EstadoReserva estado;

    public Reserva(String id, String clienteId, String hotelId,
                   LocalDate fechaInicio, LocalDate fechaFin) {
        if (id == null || id.isBlank())
            throw new BusinessRuleException("El id de la reserva no puede estar vacío");
        if (clienteId == null || clienteId.isBlank())
            throw new BusinessRuleException("El clienteId no puede estar vacío");
        if (hotelId == null || hotelId.isBlank())
            throw new BusinessRuleException("El hotelId no puede estar vacío");
        if (fechaInicio == null || fechaFin == null)
            throw new BusinessRuleException("Las fechas no pueden ser nulas");
        if (!fechaInicio.isBefore(fechaFin))
            throw new BusinessRuleException("La fecha de inicio debe ser anterior a la fecha de fin");
        if (fechaInicio.isBefore(LocalDate.now()))
            throw new BusinessRuleException("No se puede reservar en una fecha pasada");

        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        if (dias > 30)
            throw new BusinessRuleException("La estadía no puede superar los 30 días");
        if (dias < 1)
            throw new BusinessRuleException("La estadía mínima es de 1 día");

        this.id          = id;
        this.clienteId   = clienteId;
        this.hotelId     = hotelId;
        this.fechaInicio = fechaInicio;
        this.fechaFin    = fechaFin;
        this.estado      = EstadoReserva.PENDIENTE;
    }

    public void confirmar() {
        if (this.estado != EstadoReserva.PENDIENTE)
            throw new BusinessRuleException(
                    "Solo se puede confirmar una reserva en estado PENDIENTE. Estado actual: " + estado);
        this.estado = EstadoReserva.CONFIRMADA;
    }

    public void cancelar() {
        if (this.estado == EstadoReserva.COMPLETADA)
            throw new BusinessRuleException("No se puede cancelar una reserva ya completada");
        if (this.estado == EstadoReserva.CANCELADA)
            throw new BusinessRuleException("La reserva ya se encuentra cancelada");
        this.estado = EstadoReserva.CANCELADA;
    }

    public void completar() {
        if (this.estado != EstadoReserva.CONFIRMADA)
            throw new BusinessRuleException(
                    "Solo se puede completar una reserva en estado CONFIRMADA. Estado actual: " + estado);
        this.estado = EstadoReserva.COMPLETADA;
    }

    public long calcularDuracionDias() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    public boolean esCancelableAntesDeInicio() {
        return LocalDate.now().isBefore(fechaInicio) &&
                this.estado != EstadoReserva.CANCELADA &&
                this.estado != EstadoReserva.COMPLETADA;
    }

    public String getId()             { return id; }
    public String getClienteId()      { return clienteId; }
    public String getHotelId()        { return hotelId; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin()    { return fechaFin; }
    public EstadoReserva getEstado()  { return estado; }
}                                                // ← faltaba esto