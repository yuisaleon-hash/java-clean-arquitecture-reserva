package com.reserva.hotel.domain.model;

public enum EstadoReserva {
    PENDIENTE,    // recién creada, aún no gestionada — reemplaza CREADA
    CONFIRMADA,   // aceptada por el hotel
    CANCELADA,    // anulada — estado terminal
    COMPLETADA    // el huésped ya se fue — estado terminal
}