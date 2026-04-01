package com.reserva.hotel.infrastructure.adapter.in.web;

import com.reserva.hotel.application.dto.command.CreateReservaCommand;
import com.reserva.hotel.application.dto.response.ReservaResponse;
import com.reserva.hotel.application.port.in.CreateReservaUseCase;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final CreateReservaUseCase useCase;

    public ReservaController(CreateReservaUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ReservaResponse create(@RequestBody CreateReservaCommand command) {
        return useCase.execute(command);
    }
}