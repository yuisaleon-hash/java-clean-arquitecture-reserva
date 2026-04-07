package com.reserva.hotel.infrastructure.adapter.out.persistence;

import com.reserva.hotel.domain.model.Reserva;
import com.reserva.hotel.domain.repository.ReservaRepository;
import com.reserva.hotel.infrastructure.adapter.out.persistence.mapper.ReservaMapper;
import com.reserva.hotel.infrastructure.adapter.out.persistence.repository.SpringDataReservaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaReservaRepository implements ReservaRepository {

    private final SpringDataReservaRepository jpaRepository;

    public JpaReservaRepository(SpringDataReservaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Reserva save(Reserva reserva) {
        var entity = ReservaMapper.toEntity(reserva);
        var saved  = jpaRepository.save(entity);
        return ReservaMapper.toDomain(saved);
    }

    @Override
    public Optional<Reserva> findById(String id) {
        return jpaRepository.findById(id)
                .map(ReservaMapper::toDomain);
    }
}