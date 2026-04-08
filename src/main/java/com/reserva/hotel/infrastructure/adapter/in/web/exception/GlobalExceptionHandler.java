package com.reserva.hotel.infrastructure.adapter.in.web.exception;

import com.reserva.hotel.domain.exception.BusinessRuleException;
import com.reserva.hotel.domain.exception.RoomUnavailableException; // ✅ Importante
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. REGLAS DE NEGOCIO (DDD)
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessRule(BusinessRuleException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", ex.getCode());
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 2. DISPONIBILIDAD DE HABITACIÓN (Nueva Regla)
    @ExceptionHandler(RoomUnavailableException.class)
    public ResponseEntity<Map<String, Object>> handleRoomUnavailable(RoomUnavailableException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Habitación no disponible");
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());
        // Usamos 409 Conflict porque hay un conflicto con el estado actual del hotel
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // 3. ERROR INTERNO (Para depuración)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAll(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        // Cambiamos el mensaje genérico por el real para que puedas ver por qué da 500
        response.put("message", "Error interno: " + ex.getMessage());
        response.put("type", ex.getClass().getSimpleName());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}