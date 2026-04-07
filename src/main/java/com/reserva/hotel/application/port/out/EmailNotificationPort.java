package com.reserva.hotel.application.port.out;

// application/port/out/EmailNotificationPort.java
public interface EmailNotificationPort {

    void sendReservaConfirmacion(String email, String clienteNombre,
                                 String hotelNombre, String fechaInicio,
                                 String fechaFin, String reservaId);

    void sendReservaRecordatorio(String email, String clienteNombre,
                                 String hotelNombre, String fechaInicio);

    void sendReservaCancelacion(String email, String clienteNombre,
                                String hotelNombre, String fechaInicio);
}