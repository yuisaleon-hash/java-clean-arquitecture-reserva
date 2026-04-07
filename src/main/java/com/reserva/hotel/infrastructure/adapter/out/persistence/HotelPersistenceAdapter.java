package com.reserva.hotel.infrastructure.adapter.out.persistence;

import com.reserva.hotel.application.port.out.HotelPersistencePort;
import com.reserva.hotel.domain.model.Hotel;
import com.reserva.hotel.infrastructure.adapter.out.persistence.entity.HotelEntity;
import com.reserva.hotel.infrastructure.adapter.out.persistence.mapper.HotelMapper;
import com.reserva.hotel.infrastructure.adapter.out.persistence.repository.SpringDataHotelRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HotelPersistenceAdapter implements HotelPersistencePort {

    private final SpringDataHotelRepository repository;

    public HotelPersistenceAdapter(SpringDataHotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Hotel> findById(String id) {
        return repository.findById(id)
                .map(HotelMapper::toDomain);
    }

    @Override
    public Hotel save(Hotel hotel) {
        HotelEntity entity = HotelMapper.toEntity(hotel);
        HotelEntity saved = repository.save(entity);
        return HotelMapper.toDomain(saved);
    }
}