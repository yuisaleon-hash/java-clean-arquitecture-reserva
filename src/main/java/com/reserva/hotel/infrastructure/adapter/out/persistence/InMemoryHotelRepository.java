package com.reserva.hotel.infrastructure.adapter.out.persistence;

import com.reserva.hotel.application.port.out.HotelPersistencePort;
import com.reserva.hotel.domain.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryHotelRepository implements HotelPersistencePort {

    private final Map<String, Hotel> hoteles = new HashMap<>();

    @Override
    public Hotel save(Hotel hotel) {
        hoteles.put(hotel.getId(), hotel);
        return hotel;
    }

    @Override
    public Optional<Hotel> findById(String id) {
        return Optional.ofNullable(hoteles.get(id));
    }
}