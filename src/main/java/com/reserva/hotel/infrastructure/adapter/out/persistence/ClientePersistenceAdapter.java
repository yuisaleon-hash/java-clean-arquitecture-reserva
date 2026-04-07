package com.reserva.hotel.infrastructure.adapter.out.persistence;

import com.reserva.hotel.application.port.out.ClientePersistencePort;
import com.reserva.hotel.domain.model.Cliente;
import com.reserva.hotel.domain.repository.ClienteRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientePersistenceAdapter implements ClientePersistencePort {

    private final ClienteRepository repository;

    public ClientePersistenceAdapter(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cliente> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }
}