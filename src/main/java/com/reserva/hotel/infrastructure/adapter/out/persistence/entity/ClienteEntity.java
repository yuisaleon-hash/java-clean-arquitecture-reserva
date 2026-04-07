package com.reserva.hotel.infrastructure.adapter.out.persistence.entity;

import com.reserva.hotel.domain.model.valueobjects.Gender;
import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    protected ClienteEntity() {}

    public ClienteEntity(String id, String nombre, String email, Gender gender) {
        this.id     = id;
        this.nombre = nombre;
        this.email  = email;
        this.gender = gender;
    }

    public String getId()     { return id; }
    public String getNombre() { return nombre; }
    public String getEmail()  { return email; }
    public Gender getGender() { return gender; }
}