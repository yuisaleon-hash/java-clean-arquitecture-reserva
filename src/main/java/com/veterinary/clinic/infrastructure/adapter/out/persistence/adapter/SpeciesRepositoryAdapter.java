package com.veterinary.clinic.infrastructure.adapter.out.persistence.adapter;

import com.veterinary.clinic.domain.model.Species;
import com.veterinary.clinic.domain.model.valueobjects.SpeciesId;
import com.veterinary.clinic.domain.repository.SpeciesRepository;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.SpeciesJpaEntity;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository.SpeciesJpaRepository;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.mapper.SpeciesJpaMapper;
import com.veterinary.clinic.shared.annotation.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class SpeciesRepositoryAdapter implements SpeciesRepository {

    private final SpeciesJpaRepository jpaRepository;
    private final SpeciesJpaMapper mapper;

    public SpeciesRepositoryAdapter(SpeciesJpaRepository jpaRepository, SpeciesJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Species save(Species species) {
        SpeciesJpaEntity entity = mapper.toJpaEntity(species);
        SpeciesJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomainModel(savedEntity);
    }

    @Override
    public Optional<Species> findById(SpeciesId id) {
        return jpaRepository.findById(id.getValue())
                .map(mapper::toDomainModel);
    }

    @Override
    public Optional<Species> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(mapper::toDomainModel);
    }

    @Override
    public List<Species> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(SpeciesId id) {
        jpaRepository.deleteById(id.getValue());
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }
}