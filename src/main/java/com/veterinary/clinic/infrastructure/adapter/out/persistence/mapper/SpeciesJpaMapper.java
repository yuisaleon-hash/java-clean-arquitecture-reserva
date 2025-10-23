package com.veterinary.clinic.infrastructure.adapter.out.persistence.mapper;

import com.veterinary.clinic.domain.model.Species;
import com.veterinary.clinic.domain.model.valueobjects.SpeciesId;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.SpeciesJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class SpeciesJpaMapper {

    public SpeciesJpaEntity toJpaEntity(Species species) {
        SpeciesJpaEntity entity = new SpeciesJpaEntity();
        entity.setId(species.getId().getValue());
        entity.setName(species.getName());
        entity.setDescription(species.getDescription());
        return entity;
    }

    public Species toDomainModel(SpeciesJpaEntity entity) {
        return new Species(
                new SpeciesId(entity.getId()),
                entity.getName(),
                entity.getDescription()
        );
    }
}