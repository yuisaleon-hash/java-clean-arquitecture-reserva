package com.reserva.hotel.infrastructure.adapter.out.persistence;

import com.reserva.hotel.domain.model.Hotel;
import com.reserva.hotel.domain.repository.HotelRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHotelRepository implements HotelRepository {

    private final List<Hotel> hoteles = new ArrayList<>();

    @Override
    public void save(Hotel hotel) {
        hoteles.add(hotel);
    }

    @Override
    public List<Hotel> findAll() {
        return hoteles;
    }
}