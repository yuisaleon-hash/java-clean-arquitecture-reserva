package com.reserva.hotel.infrastructure.adapter.out.persistence.mapper;

import com.reserva.hotel.domain.model.Cliente;
import com.reserva.hotel.infrastructure.adapter.out.persistence.entity.ClienteEntity;

public class ClienteMapper {

    public static Cliente toDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getId(),
                entity.getNombre(),
                entity.getEmail(),
                entity.getGender()
        );
    }

    public static ClienteEntity toEntity(Cliente cliente) {
        return new ClienteEntity(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getEmail().getValue(),
                cliente.getGender()
        );
    }
}