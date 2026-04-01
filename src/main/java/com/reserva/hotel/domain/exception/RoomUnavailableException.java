package com.reserva.hotel.domain.exception;

public class RoomUnavailableException extends DomainException {

    public RoomUnavailableException() {
        super("Room is already reserved for the selected dates");
    }
}