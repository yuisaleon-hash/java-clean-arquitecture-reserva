package com.veterinary.clinic.application.mapper;

import com.veterinary.clinic.application.dto.response.PetResponse;
import com.veterinary.clinic.domain.model.Pet;
import com.veterinary.clinic.domain.model.Owner;
import com.veterinary.clinic.domain.model.Species;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    public PetResponse toResponse(Pet pet, Owner owner, Species species) {
        PetResponse response = new PetResponse();
        response.setId(pet.getId().toString());
        response.setName(pet.getName());
        response.setSpeciesName(species.getName());
        response.setBreed(pet.getBreed());
        response.setBirthDate(pet.getBirthDate());
        response.setAgeInYears(pet.getAgeInYears());
        response.setWeight(pet.getWeight() != null ? pet.getWeight().getValue() : null);
        response.setColor(pet.getColor());
        response.setGender(pet.getGender().name());
        response.setOwnerId(owner.getId().toString());
        response.setOwnerName(owner.getFullName());
        response.setActive(pet.isActive());
        return response;
    }
}