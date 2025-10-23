package com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository;

import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.PetJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetJpaRepository extends JpaRepository<PetJpaEntity, UUID> {

    List<PetJpaEntity> findByOwnerId(UUID ownerId);

    @Query("SELECT p FROM PetJpaEntity p WHERE p.ownerId = :ownerId AND p.isActive = true")
    List<PetJpaEntity> findActiveByOwnerId(@Param("ownerId") UUID ownerId);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PetJpaEntity p " +
            "WHERE p.name = :name AND p.ownerId = :ownerId AND p.isActive = true")
    boolean existsByNameAndOwnerId(@Param("name") String name, @Param("ownerId") UUID ownerId);
}