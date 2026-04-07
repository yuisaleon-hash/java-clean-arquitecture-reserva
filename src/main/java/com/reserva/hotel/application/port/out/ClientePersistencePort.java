package com.reserva.hotel.application.port.out;

import com.reserva.hotel.domain.model.Cliente;
import java.util.Optional;

public interface ClientePersistencePort {

    Optional<Cliente> findById(String id);

    Cliente save(Cliente cliente); // ✅ ESTE FALTABA
}