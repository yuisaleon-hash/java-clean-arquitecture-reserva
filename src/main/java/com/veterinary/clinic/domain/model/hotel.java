package com.example.hotel.domain.model;

public class Hotel {

    private String id;
    private String nombre;
    private int habitacionesDisponibles;

    public Hotel(String id, String nombre, int habitacionesDisponibles) {
        this.id = id;
        this.nombre = nombre;
        this.habitacionesDisponibles = habitacionesDisponibles;
    }

    public boolean tieneDisponibilidad() {
        return habitacionesDisponibles > 0;
    }

    public void reservarHabitacion() {
        if (!tieneDisponibilidad()) {
            throw new RuntimeException("No hay habitaciones disponibles");
        }
        habitacionesDisponibles--;
    }

    public String getId() {
        return id;
    }
}