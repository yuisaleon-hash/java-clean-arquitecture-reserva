package com.reserva.hotel.domain.exception;

public class RoomUnavailableException extends DomainException {

    public RoomUnavailableException() {
        super("ROOM_UNAVAILABLE", "No hay habitaciones disponibles");
    }

    public RoomUnavailableException(String hotelId) {
        super("ROOM_UNAVAILABLE",
                "No hay habitaciones disponibles en el hotel: " + hotelId);
    }

    public RoomUnavailableException(String hotelId, String fechaInicio, String fechaFin) {
        super("ROOM_UNAVAILABLE",
                "Hotel " + hotelId + " no disponible entre " + fechaInicio + " y " + fechaFin);
    }
}