package com.veterinary.clinic.application.port.in;

import com.veterinary.clinic.application.dto.command.RegisterPetCommand;
import com.veterinary.clinic.application.dto.response.PetResponse;

public interface RegisterPetUseCase {
    PetResponse register(RegisterPetCommand command);
}