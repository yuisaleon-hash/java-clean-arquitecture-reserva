package com.reserva.hotel.infrastructure.adapter.out.persistence.mapper;

import com.reserva.hotel.domain.model.Hotel;
import com.reserva.hotel.infrastructure.adapter.out.persistence.entity.HotelEntity;

public class HotelMapper {

    public static Hotel toDomain(HotelEntity entity) {
        return new Hotel(
                entity.getId(),
                entity.getNombre(),
                entity.getHabitacionesDisponibles()
        );
    }

    public static HotelEntity toEntity(Hotel hotel) {
        return new HotelEntity(
                hotel.getId(),
                hotel.getNombre(),
                hotel.getHabitacionesDisponibles()
        );
    }
}