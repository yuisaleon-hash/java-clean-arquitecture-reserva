package com.reserva.hotel.application.service;

import com.reserva.hotel.application.dto.command.CreateReservaCommand;
import com.reserva.hotel.application.dto.response.ReservaResponse;
import com.reserva.hotel.application.port.in.CreateReservaUseCase;
import com.reserva.hotel.domain.model.Reserva;
import com.reserva.hotel.domain.repository.ReservaRepository;

import org.springframework.stereotype.Service;

@Service
public class CreateReservaHandler implements CreateReservaUseCase {

    private final ReservaRepository reservaRepository;

    // ✅ ÚNICO constructor (IMPORTANTE)
    public CreateReservaHandler(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public ReservaResponse execute(CreateReservaCommand command) {

        // 1. Crear dominio
        Reserva reserva = new Reserva(
                command.getId(),
                command.getClienteId(),
                command.getHotelId(),
                command.getFechaInicio(),
                command.getFechaFin()
        );

        // 2. Guardar correctamente
        Reserva saved = reservaRepository.save(reserva);

        // 3. Respuesta
        return new ReservaResponse(
                saved.getId(),
                saved.getEstado()
        );
    }
}