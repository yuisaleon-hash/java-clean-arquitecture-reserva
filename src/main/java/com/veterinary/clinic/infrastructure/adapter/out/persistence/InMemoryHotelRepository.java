package com.veterinary.clinic.infrastructure.adapter.out.persistence;

import com.example.hotel.domain.model.Hotel;
import com.example.hotel.domain.repository.HotelRepository;

import java.util.*;

public class InMemoryHotelRepository implements HotelRepository {

    private Map<String, Hotel> db = new HashMap<>();

    public Optional<Hotel> findById(String id) {
        return Optional.ofNullable(db.get(id));
    }

    public void save(Hotel hotel) {
        db.put(hotel.getId(), hotel);
    }

    public void seed(Hotel hotel) {
        db.put(hotel.getId(), hotel);
    }
}