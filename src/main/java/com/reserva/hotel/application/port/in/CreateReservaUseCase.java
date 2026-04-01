package com.reserva.hotel.application.port.in;

import com.reserva.hotel.application.dto.command.CreateReservaCommand;
import com.reserva.hotel.application.dto.response.ReservaResponse;

public interface CreateReservaUseCase {
    ReservaResponse execute(CreateReservaCommand command);
}