package com.veterinary.clinic.domain.model.event;

import com.veterinary.clinic.domain.model.valueobjects.ReservationId;

public class ReservaCreatedEvent extends DomainEvent {

    private final ReservationId reservationId;

    public ReservaCreatedEvent(ReservationId reservationId) {
        this.reservationId = reservationId;
    }

    public ReservationId getReservationId() {
        return reservationId;
    }
}