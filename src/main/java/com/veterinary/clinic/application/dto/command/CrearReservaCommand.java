package com.veterinary.clinic.application.dto.command;

import java.time.LocalDate;
import java.util.UUID;

public record CrearReservaCommand(
        UUID guestId,
        UUID roomId,
        LocalDate checkIn,
        LocalDate checkOut
) {}