package com.reserva.hotel.application.service;

import com.reserva.hotel.application.dto.command.CreateReservaCommand;
import com.reserva.hotel.application.port.out.ClientePersistencePort;
import com.reserva.hotel.application.port.out.HotelPersistencePort;
import com.reserva.hotel.application.port.out.ReservaPersistencePort;
import com.reserva.hotel.application.port.out.EmailNotificationPort;
import com.reserva.hotel.domain.model.Cliente;
import com.reserva.hotel.domain.model.Hotel;
import com.reserva.hotel.domain.model.valueobjects.Gender;
import com.reserva.hotel.domain.service.ReservaDisponibilidadService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CreateReservaHandlerTest {

    private ClientePersistencePort clientePort;
    private HotelPersistencePort hotelPort;
    private ReservaPersistencePort reservaPort;
    private EmailNotificationPort emailPort;
    private ReservaDisponibilidadService disponibilidadService;

    private CreateReservaHandler handler;

    @BeforeEach
    void setUp() {
        clientePort = mock(ClientePersistencePort.class);
        hotelPort = mock(HotelPersistencePort.class);
        reservaPort = mock(ReservaPersistencePort.class);
        emailPort = mock(EmailNotificationPort.class);
        disponibilidadService = mock(ReservaDisponibilidadService.class);

        handler = new CreateReservaHandler(
                clientePort,
                hotelPort,
                reservaPort,
                emailPort,
                disponibilidadService
        );
    }

    @Test
    void shouldCreateReservaSuccessfully() {

        Cliente cliente = new Cliente("C001", "Juan", "juan@test.com", Gender.MASCULINO);
        Hotel hotel = new Hotel("H001", "Hotel Test", 10);

        when(clientePort.findById("C001")).thenReturn(Optional.of(cliente));
        when(hotelPort.findById("H001")).thenReturn(Optional.of(hotel));

        CreateReservaCommand command = new CreateReservaCommand(
                "R001",
                "C001",
                "H001",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3)
        );

        handler.execute(command);

        verify(reservaPort).save(any());
        verify(emailPort).sendReservaConfirmacion(
                anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString()
        );
    }
}