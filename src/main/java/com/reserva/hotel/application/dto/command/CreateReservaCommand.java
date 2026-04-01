package com.reserva.hotel.application.dto.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservaCommand {

    private String id;
    private String clienteId;
    private String hotelId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}