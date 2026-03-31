package com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.mapper;

import com.veterinary.clinic.domain.model.Reservation;
import com.veterinary.clinic.domain.model.valueobjects.*;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.ReservationJpaEntity;
import org.springframework.stereotype.Component;

public class ReservationJpaMapper {

    public ReservationJpaEntity toEntity(Reservation r) {
        return new ReservationJpaEntity(
                r.getId().value(),
                r.getGuestId().value(),
                r.getRoomId().value(),
                r.getDateRange().getCheckIn(),
                r.getDateRange().getCheckOut()
        );
    }

    public Reservation toDomain(ReservationJpaEntity e) {
        return new Reservation(
                new ReservationId(e.getId()),
                new GuestId(e.getGuestId()),
                new RoomId(e.getRoomId()),
                new DateRange(e.getCheckIn(), e.getCheckOut())
        );
    }
}