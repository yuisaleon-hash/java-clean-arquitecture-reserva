package com.reserva.hotel.infrastructure.adapter.out.persistence.mapper;

import com.reserva.hotel.domain.model.Reserva;
import com.reserva.hotel.infrastructure.adapter.out.persistence.entity.ReservaEntity;

public class ReservaMapper {

    public static Reserva toDomain(ReservaEntity entity) {
        return new Reserva(
                entity.getId(),
                entity.getClienteId(),
                entity.getHotelId(),
                entity.getFechaInicio(),
                entity.getFechaFin()
        );
    }

    public static ReservaEntity toEntity(Reserva reserva) {
        return new ReservaEntity(
                reserva.getId(),
                reserva.getClienteId(),
                reserva.getHotelId(),
                reserva.getFechaInicio(),
                reserva.getFechaFin(),
                reserva.getEstado()
        );
    }
}