package com.reserva.hotel.application.port.out;

import com.reserva.hotel.domain.model.Hotel;
import java.util.Optional;

public interface HotelPersistencePort {
    Hotel save(Hotel hotel);
    Optional<Hotel> findById(String id);
}