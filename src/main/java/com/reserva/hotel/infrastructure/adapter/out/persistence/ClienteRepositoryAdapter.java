package com.reserva.hotel.infrastructure.adapter.out.persistence;

import com.reserva.hotel.domain.model.Cliente;
import com.reserva.hotel.domain.repository.ClienteRepository;
import com.reserva.hotel.infrastructure.adapter.out.persistence.repository.SpringDataClienteRepository;
import com.reserva.hotel.infrastructure.adapter.out.persistence.mapper.ClienteMapper;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteRepositoryAdapter implements ClienteRepository {

    private final SpringDataClienteRepository jpaRepository;

    public ClienteRepositoryAdapter(SpringDataClienteRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        var entity = ClienteMapper.toEntity(cliente);
        var saved = jpaRepository.save(entity);
        return ClienteMapper.toDomain(saved);
    }

    @Override
    public Optional<Cliente> findById(String id) {
        return jpaRepository.findById(id)
                .map(ClienteMapper::toDomain);
    }
}