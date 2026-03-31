package com.veterinary.clinic.application.port.in;

import com.veterinary.clinic.application.dto.command.CrearReservaCommand;
import com.veterinary.clinic.application.dto.response.ReservaResponse;

public interface CrearReservaUseCase {

    ReservaResponse ejecutar(CrearReservaCommand command);
}