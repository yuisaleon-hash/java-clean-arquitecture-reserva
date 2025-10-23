package com.veterinary.clinic.domain.repository;

import com.veterinary.clinic.domain.model.Owner;
import com.veterinary.clinic.domain.model.valueobjects.OwnerId;
import com.veterinary.clinic.domain.model.valueobjects.Email;
import java.util.List;
import java.util.Optional;

public interface OwnerRepository {

    Owner save(Owner owner);

    Optional<Owner> findById(OwnerId id);

    Optional<Owner> findByEmail(Email email);

    List<Owner> findAll();

    void delete(OwnerId id);

    boolean existsByEmail(Email email);
}