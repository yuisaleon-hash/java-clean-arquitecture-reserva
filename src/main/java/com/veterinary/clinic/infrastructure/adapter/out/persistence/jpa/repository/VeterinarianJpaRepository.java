package com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository;

import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.VeterinarianJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VeterinarianJpaRepository extends JpaRepository<VeterinarianJpaEntity, UUID> {

    Optional<VeterinarianJpaEntity> findByEmail(String email);

    Optional<VeterinarianJpaEntity> findByLicenseNumber(String licenseNumber);

    @Query("SELECT v FROM VeterinarianJpaEntity v WHERE v.isActive = true")
    List<VeterinarianJpaEntity> findActiveVeterinarians();

    boolean existsByEmail(String email);

    boolean existsByLicenseNumber(String licenseNumber);
}