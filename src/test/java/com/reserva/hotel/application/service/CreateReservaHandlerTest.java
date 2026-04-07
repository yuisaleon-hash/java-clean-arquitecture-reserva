package com.reserva.hotel.application.service;

import com.reserva.hotel.application.dto.command.CreateReservaCommand;
import com.reserva.hotel.application.port.out.*;
import com.reserva.hotel.domain.model.*;
import com.reserva.hotel.domain.model.valueobjects.Gender;
import com.reserva.hotel.domain.service.ReservaDisponibilidadService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateReservaHandlerTest {

    @Test
    void debeCrearReservaCorrectamente() {

        // 🔹 Mocks de puertos (HEXAGONAL ✔)
        ReservaPersistencePort reservaPort = Mockito.mock(ReservaPersistencePort.class);
        HotelPersistencePort hotelPort = Mockito.mock(HotelPersistencePort.class);
        ClientePersistencePort clientePort = Mockito.mock(ClientePersistencePort.class);
        EmailNotificationPort emailPort = Mockito.mock(EmailNotificationPort.class);

        // 🔹 Servicio de dominio (DDD ✔)
        ReservaDisponibilidadService disponibilidadService = new ReservaDisponibilidadService();

        // 🔹 Entidades reales (DOMINIO ✔)
        Hotel hotel = new Hotel("1", "Hotel Test", 5);
        Cliente cliente = new Cliente("1", "Juan", "test@mail.com", Gender.MASCULINO);;

        // 🔹 Comportamiento de puertos (MOCKS ✔)
        Mockito.when(hotelPort.findById("1")).thenReturn(Optional.of(hotel));
        Mockito.when(clientePort.findById("1")).thenReturn(Optional.of(cliente));

        Mockito.when(reservaPort.save(Mockito.any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // 🔹 Caso de uso (APPLICATION ✔)
        CreateReservaHandler handler = new CreateReservaHandler(
                reservaPort,
                hotelPort,
                clientePort,
                emailPort,
                disponibilidadService
        );

        // 🔹 Comando (DTO ✔)
        CreateReservaCommand command = new CreateReservaCommand(
                "1",
                "1",
                "1",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3)
        );

        // 🔹 Ejecución
        var response = handler.execute(command);

        // 🔹 Validaciones
        assertNotNull(response);
        assertEquals("1", response.getId());
    }
}