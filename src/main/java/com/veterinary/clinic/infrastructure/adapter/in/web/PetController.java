package com.veterinary.clinic.infrastructure.adapter.in.web;

import com.veterinary.clinic.application.port.in.RegisterPetUseCase;
import com.veterinary.clinic.application.dto.command.RegisterPetCommand;
import com.veterinary.clinic.application.dto.response.PetResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pets")
@Tag(name = "Pets", description = "Gestión de mascotas")
@CrossOrigin(origins = "*")
public class PetController {

    private final RegisterPetUseCase registerPetUseCase;

    public PetController(RegisterPetUseCase registerPetUseCase) {
        this.registerPetUseCase = registerPetUseCase;
    }

    @PostMapping
    @Operation(summary = "Registrar nueva mascota", description = "Registra una nueva mascota en el sistema")
    public ResponseEntity<PetResponse> registerPet(@Valid @RequestBody RegisterPetCommand command) {
        PetResponse response = registerPetUseCase.register(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "Obtener mascotas por dueño", description = "Obtiene todas las mascotas de un dueño específico")
    public ResponseEntity<List<PetResponse>> getPetsByOwner(@PathVariable String ownerId) {
        // Implementar caso de uso para obtener mascotas por dueño
        return ResponseEntity.ok().build();
    }
}