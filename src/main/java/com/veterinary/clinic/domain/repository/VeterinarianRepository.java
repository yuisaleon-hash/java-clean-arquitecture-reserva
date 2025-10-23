package com.veterinary.clinic.domain.repository;

import com.veterinary.clinic.domain.model.Veterinarian;
import com.veterinary.clinic.domain.model.valueobjects.VeterinarianId;
import com.veterinary.clinic.domain.model.valueobjects.Email;
import java.util.List;
import java.util.Optional;

public interface VeterinarianRepository {

    Veterinarian save(Veterinarian veterinarian);

    Optional<Veterinarian> findById(VeterinarianId id);

    Optional<Veterinarian> findByEmail(Email email);

    Optional<Veterinarian> findByLicenseNumber(String licenseNumber);

    List<Veterinarian> findAll();

    List<Veterinarian> findActiveVeterinarians();

    void delete(VeterinarianId id);

    boolean existsByEmail(Email email);

    boolean existsByLicenseNumber(String licenseNumber);
}