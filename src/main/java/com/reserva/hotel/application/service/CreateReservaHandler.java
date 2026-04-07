package com.reserva.hotel.application.service;

import com.reserva.hotel.application.dto.command.CreateReservaCommand;
import com.reserva.hotel.application.dto.response.ReservaResponse;
import com.reserva.hotel.application.port.in.CreateReservaUseCase;
import com.reserva.hotel.application.port.out.*;

import com.reserva.hotel.domain.model.Cliente;
import com.reserva.hotel.domain.model.Hotel;
import com.reserva.hotel.domain.model.Reserva;
import com.reserva.hotel.domain.exception.BusinessRuleException;
import com.reserva.hotel.domain.service.ReservaDisponibilidadService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateReservaHandler implements CreateReservaUseCase {

    private final ReservaPersistencePort reservaPersistence;
    private final HotelPersistencePort hotelPersistence;
    private final ClientePersistencePort clientePersistence;
    private final EmailNotificationPort emailNotification;
    private final ReservaDisponibilidadService disponibilidadService;

    @Override
    public ReservaResponse execute(CreateReservaCommand command) {

        Hotel hotel = hotelPersistence.findById(command.getHotelId())
                .orElseThrow(() -> new BusinessRuleException("Hotel no encontrado"));

        Cliente cliente = clientePersistence.findById(command.getClienteId())
                .orElseThrow(() -> new BusinessRuleException("Cliente no encontrado"));

        Reserva reserva = new Reserva(
                command.getId(),
                command.getClienteId(),
                command.getHotelId(),
                command.getFechaInicio(),
                command.getFechaFin()
        );

      // disponibilidadService.validarDisponibilidad(hotel, reserva);

        hotel.reservarHabitacion();
        reserva.confirmar();

        hotelPersistence.save(hotel);
        Reserva saved = reservaPersistence.save(reserva);

        emailNotification.sendReservaConfirmacion(
                cliente.getEmail(),
                cliente.getNombre(),
                hotel.getNombre(),
                saved.getFechaInicio().toString(),
                saved.getFechaFin().toString(),
                saved.getId()
        );

        return new ReservaResponse(saved.getId(), saved.getEstado());
    }
}