package com.example.hotel.domain.repository;

import com.example.hotel.domain.model.Hotel;

import java.util.Optional;

public interface HotelRepository {
    Optional<Hotel> findById(String id);
    void save(Hotel hotel);
}