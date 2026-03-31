package com.veterinary.clinic.application.port.in;

import com.veterinary.clinic.application.dto.command.CrearReservaCommand;
import com.veterinary.clinic.application.dto.response.ReservationResponse;

public interface CrearReservaUseCase {

    ReservationResponse execute(CrearReservaCommand command);
}