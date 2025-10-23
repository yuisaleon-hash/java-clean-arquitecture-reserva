package com.veterinary.clinic.infrastructure.adapter.out.persistence.mapper;

import com.veterinary.clinic.domain.model.Pet;
import com.veterinary.clinic.domain.model.Species;
import com.veterinary.clinic.domain.model.valueobjects.*;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.PetJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class PetJpaMapper {

    public PetJpaEntity toJpaEntity(Pet pet) {
        PetJpaEntity entity = new PetJpaEntity();
        entity.setId(pet.getId().getValue());
        entity.setName(pet.getName());
        entity.setSpeciesId(pet.getSpecies().getId().getValue());
        entity.setBreed(pet.getBreed());
        entity.setBirthDate(pet.getBirthDate());
        entity.setWeight(pet.getWeight() != null ? pet.getWeight().getValue() : null);
        entity.setColor(pet.getColor());
        entity.setGender(PetJpaEntity.GenderJpa.valueOf(pet.getGender().name()));
        entity.setOwnerId(pet.getOwnerId().getValue());
        entity.setIsActive(pet.isActive());
        return entity;
    }

    public Pet toDomainModel(PetJpaEntity entity) {
        // Crear Species básico (en una implementación real cargarías desde BD)
        Species species = new Species(
                new SpeciesId(entity.getSpeciesId()),
                "Unknown", // Deberías cargar desde base de datos
                "Unknown"
        );

        return new Pet(
                new PetId(entity.getId()),
                entity.getName(),
                species,
                entity.getBreed(),
                entity.getBirthDate(),
                entity.getWeight() != null ? Weight.of(entity.getWeight().doubleValue()) : null,
                entity.getColor(),
                Gender.valueOf(entity.getGender().name()),
                new OwnerId(entity.getOwnerId())
        );
    }
}