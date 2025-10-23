package com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository;

import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.OwnerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OwnerJpaRepository extends JpaRepository<OwnerJpaEntity, UUID> {

    Optional<OwnerJpaEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}