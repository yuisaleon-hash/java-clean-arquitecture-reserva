package com.reserva.hotel.application.port.out;

import com.reserva.hotel.domain.model.Cliente;
import java.util.Optional;

public interface ClientePersistencePort {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(String id);
}