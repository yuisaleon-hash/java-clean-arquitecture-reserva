package com.reserva.hotel.domain.model;

import com.reserva.hotel.domain.exception.BusinessRuleException;
import com.reserva.hotel.domain.model.valueobjects.Email;
import com.reserva.hotel.domain.model.valueobjects.Gender;

public class Cliente {

    private final String id;
    private String nombre;
    private Email email;
    private Gender gender;

    public Cliente(String id, String nombre, String email, Gender gender) {
        if (id == null || id.isBlank()) {
            throw new BusinessRuleException("El id del cliente no puede estar vacío");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new BusinessRuleException("El nombre del cliente no puede estar vacío");
        }
        this.id     = id;
        this.nombre = nombre;
        this.email  = new Email(email);
        this.gender = gender;
    }

    public void actualizarEmail(String nuevoEmail) {
        this.email = new Email(nuevoEmail);
    }

    public void actualizarNombre(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new BusinessRuleException("El nombre no puede estar vacío");
        }
        this.nombre = nuevoNombre;
    }

    public String getId()     { return id; }
    public String getNombre() { return nombre; }
    public String getEmail()  { return email.getValue(); }
    public Gender getGender() { return gender; }
}