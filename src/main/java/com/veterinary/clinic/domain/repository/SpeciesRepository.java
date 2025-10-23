package com.veterinary.clinic.domain.repository;

import com.veterinary.clinic.domain.model.Species;
import com.veterinary.clinic.domain.model.valueobjects.SpeciesId;
import java.util.List;
import java.util.Optional;

public interface SpeciesRepository {

    Species save(Species species);

    Optional<Species> findById(SpeciesId id);

    Optional<Species> findByName(String name);

    List<Species> findAll();

    void delete(SpeciesId id);

    boolean existsByName(String name);
}