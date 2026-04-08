package com.reserva.hotel.application.service;

import com.reserva.hotel.application.dto.command.CreateReservaCommand;
import com.reserva.hotel.application.dto.response.ReservaResponse;
import com.reserva.hotel.application.port.in.CreateReservaUseCase;
import com.reserva.hotel.application.port.out.ClientePersistencePort;
import com.reserva.hotel.application.port.out.HotelPersistencePort;
import com.reserva.hotel.application.port.out.ReservaPersistencePort;
import com.reserva.hotel.application.port.out.EmailNotificationPort;

import com.reserva.hotel.domain.exception.BusinessRuleException;
import com.reserva.hotel.domain.model.Cliente;
import com.reserva.hotel.domain.model.Hotel;
import com.reserva.hotel.domain.model.Reserva;
import com.reserva.hotel.domain.service.ReservaDisponibilidadService;

import org.springframework.stereotype.Service;

@Service
public class CreateReservaHandler implements CreateReservaUseCase {

    private final ClientePersistencePort clientePort;
    private final HotelPersistencePort hotelPort;
    private final ReservaPersistencePort reservaPort;
    private final EmailNotificationPort emailNotificationPort;
    private final ReservaDisponibilidadService disponibilidadService;

    // ✅ CONSTRUCTOR CORRECTO
    public CreateReservaHandler(
            ClientePersistencePort clientePort,
            HotelPersistencePort hotelPort,
            ReservaPersistencePort reservaPort,
            EmailNotificationPort emailNotificationPort,
            ReservaDisponibilidadService disponibilidadService
    ) {
        this.clientePort = clientePort;
        this.hotelPort = hotelPort;
        this.reservaPort = reservaPort;
        this.emailNotificationPort = emailNotificationPort;
        this.disponibilidadService = disponibilidadService;
    }

    @Override
    public ReservaResponse execute(CreateReservaCommand command) {

        // 🔍 Obtener cliente
        Cliente cliente = clientePort.findById(command.getClienteId())
                .orElseThrow(() -> new BusinessRuleException("Cliente no encontrado"));

        // 🔍 Obtener hotel
        Hotel hotel = hotelPort.findById(command.getHotelId())
                .orElseThrow(() -> new BusinessRuleException("Hotel no encontrado"));

        // 🧠 Crear entidad Reserva (DOMINIO)
        Reserva reserva = new Reserva(
                command.getId(),
                command.getClienteId(),
                command.getHotelId(),
                command.getFechaInicio(),
                command.getFechaFin()
        );

        // ✅ VALIDACIÓN DE DOMINIO (CORRECTA)
        disponibilidadService.validarDisponibilidad(hotel, reserva);

        // 💾 Guardar
        reservaPort.save(reserva);

        // 📧 Enviar email (usar String, NO ValueObject)
        emailNotificationPort.sendReservaConfirmacion(
                cliente.getEmail().getValue(),
                cliente.getNombre(),
                hotel.getNombre(),
                reserva.getFechaInicio().toString(),
                reserva.getFechaFin().toString(),
                reserva.getId()
        );

        // ✅ RESPUESTA (ajustada a tu constructor real)
        return new ReservaResponse(
                reserva.getId(),              // 1. String
                reserva.getClienteId(),       // 2. String
                reserva.getHotelId(),         // 3. String
                reserva.getFechaInicio(),      // 4. LocalDate
                reserva.getFechaFin(),         // 5. LocalDate
                reserva.getEstado(),           // 6. EstadoReserva
                "Reserva creada exitosamente"  // 7. String mensaje
        );
    }
}