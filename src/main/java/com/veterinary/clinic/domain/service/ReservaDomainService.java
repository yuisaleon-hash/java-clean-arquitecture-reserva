package com.veterinary.clinic.domain.service;

import com.veterinary.clinic.domain.exception.RoomUnavailableException;
import com.veterinary.clinic.domain.model.Reservation;

public class ReservaDomainService {

    public void validateReservation(Reservation reservation, boolean roomOccupied) {

        if (roomOccupied) {
            throw new RoomUnavailableException();
        }

        // aquí puedes agregar más reglas
    }
}