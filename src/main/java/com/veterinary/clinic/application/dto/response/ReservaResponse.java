package com.veterinary.clinic.application.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record ReservaResponse(
        UUID reservaId,
        UUID guestId,
        UUID roomId,
        LocalDate checkIn,
        LocalDate checkOut
) {}