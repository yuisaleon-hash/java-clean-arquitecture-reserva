package com.reserva.hotel.domain.repository;

import com.reserva.hotel.domain.model.Cliente;   // ✅ IMPORT OBLIGATORIO
import java.util.Optional;                       // ✅ IMPORT OBLIGATORIO

public interface ClienteRepository {

    Cliente save(Cliente cliente);

    Optional<Cliente> findById(String id);
}