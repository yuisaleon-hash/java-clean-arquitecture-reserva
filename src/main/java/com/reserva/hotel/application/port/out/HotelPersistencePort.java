package com.reserva.hotel.application.port.out;

import com.reserva.hotel.domain.model.Hotel;
import java.util.Optional;

public interface HotelPersistencePort {
    Optional<Hotel> findById(String id);
    Hotel save(Hotel hotel);
}