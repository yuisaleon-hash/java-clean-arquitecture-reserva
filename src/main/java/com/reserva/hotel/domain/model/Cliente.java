package com.reserva.hotel.domain.model;

public class Cliente {

    private String id;
    private String nombre;
    private String email;

    public Cliente(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public String getId() {
        return id;
    }
}