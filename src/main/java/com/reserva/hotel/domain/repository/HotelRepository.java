package com.reserva.hotel.domain.repository;

import com.reserva.hotel.domain.model.Hotel;

import java.util.List;

public interface HotelRepository {
    void save(Hotel hotel);
    List<Hotel> findAll();
}