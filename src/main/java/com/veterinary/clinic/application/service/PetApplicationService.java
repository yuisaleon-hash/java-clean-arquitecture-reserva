package com.veterinary.clinic.application.service;

import com.veterinary.clinic.application.port.in.RegisterPetUseCase;
import com.veterinary.clinic.application.dto.command.RegisterPetCommand;
import com.veterinary.clinic.application.dto.response.PetResponse;
import com.veterinary.clinic.application.mapper.PetMapper;
import com.veterinary.clinic.domain.model.Pet;
import com.veterinary.clinic.domain.model.Owner;
import com.veterinary.clinic.domain.model.Species;
import com.veterinary.clinic.domain.model.valueobjects.*;
import com.veterinary.clinic.domain.repository.PetRepository;
import com.veterinary.clinic.domain.repository.OwnerRepository;
import com.veterinary.clinic.domain.repository.SpeciesRepository;
import com.veterinary.clinic.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
public class PetApplicationService implements RegisterPetUseCase {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final SpeciesRepository speciesRepository;
    private final PetMapper petMapper;

    public PetApplicationService(PetRepository petRepository,
                                 OwnerRepository ownerRepository,
                                 SpeciesRepository speciesRepository,
                                 PetMapper petMapper) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
        this.speciesRepository = speciesRepository;
        this.petMapper = petMapper;
    }

    @Override
    public PetResponse register(RegisterPetCommand command) {
        // Validar que el dueño existe
        Owner owner = ownerRepository.findById(OwnerId.of(command.getOwnerId()))
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        // Validar que la especie existe
        Species species = speciesRepository.findById(SpeciesId.of(command.getSpeciesId()))
                .orElseThrow(() -> new RuntimeException("Species not found"));

        // Validar que no existe otra mascota con el mismo nombre para este dueño
        if (petRepository.existsByNameAndOwnerId(command.getName(), owner.getId())) {
            throw new RuntimeException("Pet with this name already exists for this owner");
        }

        // Crear la mascota
        Pet pet = Pet.create(
                command.getName(),
                species,
                command.getBreed(),
                command.getBirthDate(),
                command.getWeight() != null ? Weight.of(command.getWeight().doubleValue()) : null,
                command.getColor(),
                Gender.valueOf(command.getGender().toUpperCase()),
                owner.getId()
        );

        // Guardar la mascota
        Pet savedPet = petRepository.save(pet);

        // Convertir a DTO de respuesta
        return petMapper.toResponse(savedPet, owner, species);
    }
}