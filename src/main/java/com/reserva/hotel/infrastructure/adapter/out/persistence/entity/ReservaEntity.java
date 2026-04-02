package com.reserva.hotel.infrastructure.adapter.out.persistence.entity;

import com.reserva.hotel.domain.model.EstadoReserva;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reserva")
public class ReservaEntity {

    @Id
    private String id;

    private String clienteId;
    private String hotelId;

    @Column(name = "fecha_ingreso")   // ← mantiene nombre de columna en BD
    private LocalDate fechaInicio;    // ← nombre Java consistente con dominio

    @Column(name = "fecha_salida")    // ← mantiene nombre de columna en BD
    private LocalDate fechaFin;       // ← nombre Java consistente con dominio

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    protected ReservaEntity() {}

    public ReservaEntity(String id,
                         String clienteId,
                         String hotelId,
                         LocalDate fechaInicio,
                         LocalDate fechaFin,
                         EstadoReserva estado) {
        this.id          = id;
        this.clienteId   = clienteId;
        this.hotelId     = hotelId;
        this.fechaInicio = fechaInicio;
        this.fechaFin    = fechaFin;
        this.estado      = estado;
    }

    public String getId()             { return id; }
    public String getClienteId()      { return clienteId; }
    public String getHotelId()        { return hotelId; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin()    { return fechaFin; }
    public EstadoReserva getEstado()  { return estado; }
}