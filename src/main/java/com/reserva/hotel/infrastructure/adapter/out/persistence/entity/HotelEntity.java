package com.reserva.hotel.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hotel")
public class HotelEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int habitacionesDisponibles;

    protected HotelEntity() {}

    public HotelEntity(String id, String nombre, int habitacionesDisponibles) {
        this.id                      = id;
        this.nombre                  = nombre;
        this.habitacionesDisponibles = habitacionesDisponibles;
    }

    public String getId()                         { return id; }
    public String getNombre()                     { return nombre; }
    public int getHabitacionesDisponibles()       { return habitacionesDisponibles; }
    public void setHabitacionesDisponibles(int h) { this.habitacionesDisponibles = h; }
}