package com.veterinary.clinic.application.service;
{
import com.example.hotel.domain.repository.*;
import com.example.hotel.domain.service.ReservaService;
import com.example.hotel.application.command.CrearReservaCommand;
import com.example.hotel.domain.model.*;

public class CrearReservaHandler {

    private final ReservaRepository reservaRepo;
    private final HotelRepository hotelRepo;
    private final ReservaService service;

    public CrearReservaHandler(ReservaRepository reservaRepo,
                               HotelRepository hotelRepo,
                               ReservaService service) {
        this.reservaRepo = reservaRepo;
        this.hotelRepo = hotelRepo;
        this.service = service;
    }

    public Reserva execute(CrearReservaCommand cmd) {

        Hotel hotel = hotelRepo.findById(cmd.hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado"));

        Reserva reserva = service.crearReserva(
                hotel,
                cmd.clienteId,
                cmd.fechaInicio,
                cmd.fechaFin
        );

        reservaRepo.save(reserva);
        hotelRepo.save(hotel);

        return reserva;
    }
}
}