package com.veterinary.clinic.domain.repository;

import com.veterinary.clinic.domain.model.Pet;
import com.veterinary.clinic.domain.model.valueobjects.PetId;
import com.veterinary.clinic.domain.model.valueobjects.OwnerId;
import java.util.List;
import java.util.Optional;

public interface PetRepository {

    Pet save(Pet pet);

    Optional<Pet> findById(PetId id);

    List<Pet> findByOwnerId(OwnerId ownerId);

    List<Pet> findActiveByOwnerId(OwnerId ownerId);

    List<Pet> findAll();

    void delete(PetId id);

    boolean existsByNameAndOwnerId(String name, OwnerId ownerId);
}