package com.reserva.hotel.application.service;

import com.reserva.hotel.application.dto.command.CreateReservaCommand;
import com.reserva.hotel.application.dto.response.ReservaResponse;
import com.reserva.hotel.application.port.in.CreateReservaUseCase;
import com.reserva.hotel.application.port.out.ReservaPersistencePort;
import com.reserva.hotel.domain.model.Reserva;
import com.reserva.hotel.domain.service.ReservaDomainService;

import java.util.UUID;

public class CreateReservaHandler implements CreateReservaUseCase {

    private final ReservaPersistencePort reservaPersistencePort;
    private final ReservaDomainService reservaDomainService;

    public CreateReservaHandler(ReservaPersistencePort reservaPersistencePort,
                                ReservaDomainService reservaDomainService) {
        this.reservaPersistencePort = reservaPersistencePort;
        this.reservaDomainService = reservaDomainService;
    }

    @Override
    public ReservaResponse execute(CreateReservaCommand command) {

        reservaDomainService.validarDisponibilidad(
                command.getHotelId(),
                command.getFechaInicio(),
                command.getFechaFin()
        );

        Reserva reserva = new Reserva(
                command.getId(),
                command.getClienteId(),
                command.getHotelId(),
                command.getFechaInicio(),
                command.getFechaFin()
        );

        reservaPersistencePort.save(reserva);

        return new ReservaResponse(
                reserva.getId(),
                reserva.getEstado().name()
        );
    }
}