package com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository;

import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.SpeciesJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpeciesJpaRepository extends JpaRepository<SpeciesJpaEntity, UUID> {

    Optional<SpeciesJpaEntity> findByName(String name);

    boolean existsByName(String name);
}