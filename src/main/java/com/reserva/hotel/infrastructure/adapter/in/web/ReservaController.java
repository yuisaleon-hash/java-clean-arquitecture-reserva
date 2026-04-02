package com.reserva.hotel.infrastructure.adapter.in.web;

import com.reserva.hotel.application.dto.command.CreateReservaCommand;
import com.reserva.hotel.application.dto.response.ReservaResponse;
import com.reserva.hotel.application.dto.query.GetReservaHandler;
import com.reserva.hotel.application.port.in.CreateReservaUseCase;
import com.reserva.hotel.domain.model.Reserva;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final CreateReservaUseCase useCase;
    private final GetReservaHandler getReservaHandler;

    public ReservaController(CreateReservaUseCase useCase,
                             GetReservaHandler getReservaHandler) {
        this.useCase = useCase;
        this.getReservaHandler = getReservaHandler;
    }

    // ✅ Crear reserva
    @PostMapping
    public ReservaResponse create(@RequestBody CreateReservaCommand command) {
        return useCase.execute(command);
    }

    // ✅ Obtener reserva por ID
    @GetMapping("/{id}")
    public Reserva getById(@PathVariable String id) {
        return getReservaHandler.execute(id);
    }
}