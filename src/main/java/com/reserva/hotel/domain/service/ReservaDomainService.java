package com.reserva.hotel.domain.service;

import com.reserva.hotel.domain.exception.BusinessRuleException;
import com.reserva.hotel.domain.exception.RoomUnavailableException;
import com.reserva.hotel.domain.model.Cliente;
import com.reserva.hotel.domain.model.Hotel;
import com.reserva.hotel.domain.model.Reserva;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ReservaDomainService {

    private static final int MIN_DIAS_RESERVA    = 1;
    private static final int MAX_DIAS_RESERVA    = 30;
    private static final int MAX_DIAS_ANTICIPACION = 365;

    // ── Validaciones de fechas ──────────────────────────────────────────

    public void validarFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new BusinessRuleException("Las fechas de la reserva no pueden ser nulas");
        }
        if (!fechaInicio.isBefore(fechaFin)) {
            throw new BusinessRuleException(
                    "La fecha de inicio debe ser anterior a la fecha de fin");
        }
        if (fechaInicio.isBefore(LocalDate.now())) {
            throw new BusinessRuleException(
                    "La fecha de inicio no puede ser en el pasado");
        }
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        if (dias < MIN_DIAS_RESERVA) {
            throw new BusinessRuleException(
                    "La reserva debe ser de al menos " + MIN_DIAS_RESERVA + " día");
        }
        if (dias > MAX_DIAS_RESERVA) {
            throw new BusinessRuleException(
                    "La reserva no puede superar " + MAX_DIAS_RESERVA + " días");
        }
        if (ChronoUnit.DAYS.between(LocalDate.now(), fechaInicio) > MAX_DIAS_ANTICIPACION) {
            throw new BusinessRuleException(
                    "No se puede reservar con más de " + MAX_DIAS_ANTICIPACION + " días de anticipación");
        }
    }

    // ── Validaciones del hotel ──────────────────────────────────────────

    public void validarDisponibilidadHotel(Hotel hotel) {
        if (hotel == null) {
            throw new BusinessRuleException("El hotel no existe");
        }
        if (!hotel.tieneDisponibilidad()) {
            throw new RoomUnavailableException(hotel.getId());
        }
    }

    // ── Validaciones del cliente ────────────────────────────────────────

    public void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new BusinessRuleException("El cliente no existe");
        }
    }

    // ── Creación de la reserva (orquesta todas las reglas) ──────────────

    public Reserva crearReserva(String id,
                                Cliente cliente,
                                Hotel hotel,
                                LocalDate fechaInicio,
                                LocalDate fechaFin) {
        // 1. Validar fechas
        validarFechas(fechaInicio, fechaFin);

        // 2. Validar cliente
        validarCliente(cliente);

        // 3. Validar disponibilidad del hotel
        validarDisponibilidadHotel(hotel);

        // 4. Reducir habitaciones disponibles en el hotel
        hotel.reservarHabitacion();

        // 5. Crear y retornar la reserva
        return new Reserva(
                id,
                cliente.getId(),
                hotel.getId(),
                fechaInicio,
                fechaFin
        );
    }

    // ── Cancelación de reserva ──────────────────────────────────────────

    public void cancelarReserva(Reserva reserva, Hotel hotel) {
        if (reserva == null) {
            throw new BusinessRuleException("La reserva no existe");
        }

        // Delega la validación de estado a la entidad Reserva
        reserva.cancelar();

        // Libera la habitación en el hotel
        hotel.liberarHabitacion();
    }

    // ── Confirmación de reserva ─────────────────────────────────────────

    public void confirmarReserva(Reserva reserva) {
        if (reserva == null) {
            throw new BusinessRuleException("La reserva no existe");
        }
        reserva.confirmar();
    }

    // ── Completar reserva ───────────────────────────────────────────────

    public void completarReserva(Reserva reserva, Hotel hotel) {
        if (reserva == null) {
            throw new BusinessRuleException("La reserva no existe");
        }
        reserva.completar();

        // Al completarse, la habitación queda libre para nuevas reservas
        hotel.liberarHabitacion();
    }
}