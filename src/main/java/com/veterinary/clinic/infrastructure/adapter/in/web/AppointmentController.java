package com.veterinary.clinic.infrastructure.adapter.in.web;

import com.veterinary.clinic.application.port.in.ScheduleAppointmentUseCase;
import com.veterinary.clinic.application.port.in.FindAppointmentsByDateUseCase;
import com.veterinary.clinic.application.dto.command.ScheduleAppointmentCommand;
import com.veterinary.clinic.application.dto.query.FindAppointmentsByDateQuery;
import com.veterinary.clinic.application.dto.response.AppointmentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@Tag(name = "Appointments", description = "Gestión de citas veterinarias")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final ScheduleAppointmentUseCase scheduleAppointmentUseCase;
    private final FindAppointmentsByDateUseCase findAppointmentsByDateUseCase;

    public AppointmentController(ScheduleAppointmentUseCase scheduleAppointmentUseCase,
                                 FindAppointmentsByDateUseCase findAppointmentsByDateUseCase) {
        this.scheduleAppointmentUseCase = scheduleAppointmentUseCase;
        this.findAppointmentsByDateUseCase = findAppointmentsByDateUseCase;
    }

    @PostMapping
    @Operation(summary = "Programar nueva cita", description = "Programa una nueva cita veterinaria")
    @ApiResponse(responseCode = "201", description = "Cita programada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @ApiResponse(responseCode = "409", description = "Conflicto de horarios")
    public ResponseEntity<AppointmentResponse> scheduleAppointment(
            @Valid @RequestBody ScheduleAppointmentCommand command) {

        AppointmentResponse response = scheduleAppointmentUseCase.schedule(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Buscar citas por fecha", description = "Obtiene todas las citas de una fecha específica")
    @ApiResponse(responseCode = "200", description = "Lista de citas encontradas")
    public ResponseEntity<List<AppointmentResponse>> findAppointmentsByDate(
            @RequestParam LocalDate date,
            @RequestParam(required = false) String veterinarianId,
            @RequestParam(required = false) String status) {

        FindAppointmentsByDateQuery query = new FindAppointmentsByDateQuery(date, veterinarianId, status);
        List<AppointmentResponse> appointments = findAppointmentsByDateUseCase.findByDate(query);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{appointmentId}")
    @Operation(summary = "Obtener cita por ID", description = "Obtiene los detalles de una cita específica")
    public ResponseEntity<AppointmentResponse> getAppointmentById(
            @PathVariable String appointmentId) {
        // Implementar caso de uso para obtener cita por ID
        // Por simplicidad, no implementado en este ejemplo
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{appointmentId}/confirm")
    @Operation(summary = "Confirmar cita", description = "Confirma una cita programada")
    public ResponseEntity<Void> confirmAppointment(@PathVariable String appointmentId) {
        // Implementar caso de uso para confirmar cita
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{appointmentId}/cancel")
    @Operation(summary = "Cancelar cita", description = "Cancela una cita existente")
    public ResponseEntity<Void> cancelAppointment(@PathVariable String appointmentId) {
        // Implementar caso de uso para cancelar cita
        return ResponseEntity.ok().build();
    }
}