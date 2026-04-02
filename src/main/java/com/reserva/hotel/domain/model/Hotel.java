package com.reserva.hotel.domain.model;

import com.reserva.hotel.domain.exception.BusinessRuleException;
import com.reserva.hotel.domain.exception.RoomUnavailableException;

public class Hotel {

    private final String id;
    private final String nombre;
    private int habitacionesDisponibles;

    public Hotel(String id, String nombre, int habitacionesDisponibles) {
        if (id == null || id.isBlank()) {
            throw new BusinessRuleException("El id del hotel no puede estar vacío");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new BusinessRuleException("El nombre del hotel no puede estar vacío");
        }
        if (habitacionesDisponibles < 0) {
            throw new BusinessRuleException("Las habitaciones disponibles no pueden ser negativas");
        }
        this.id                      = id;
        this.nombre                  = nombre;
        this.habitacionesDisponibles = habitacionesDisponibles;
    }

    public boolean tieneDisponibilidad() {
        return habitacionesDisponibles > 0;
    }

    public void reservarHabitacion() {
        if (!tieneDisponibilidad()) {
            throw new RoomUnavailableException(id, "N/A", "N/A");
        }
        habitacionesDisponibles--;
    }

    public void liberarHabitacion() {
        habitacionesDisponibles++;
    }

    public String getId()                   { return id; }
    public String getNombre()               { return nombre; }
    public int getHabitacionesDisponibles() { return habitacionesDisponibles; }
}