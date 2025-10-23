package com.veterinary.clinic.infrastructure.adapter.out.persistence.adapter;

import com.veterinary.clinic.domain.model.Pet;
import com.veterinary.clinic.domain.model.valueobjects.PetId;
import com.veterinary.clinic.domain.model.valueobjects.OwnerId;
import com.veterinary.clinic.domain.repository.PetRepository;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.PetJpaEntity;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository.PetJpaRepository;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.mapper.PetJpaMapper;
import com.veterinary.clinic.shared.annotation.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class PetRepositoryAdapter implements PetRepository {

    private final PetJpaRepository jpaRepository;
    private final PetJpaMapper mapper;

    public PetRepositoryAdapter(PetJpaRepository jpaRepository, PetJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Pet save(Pet pet) {
        PetJpaEntity entity = mapper.toJpaEntity(pet);
        PetJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomainModel(savedEntity);
    }

    @Override
    public Optional<Pet> findById(PetId id) {
        return jpaRepository.findById(id.getValue())
                .map(mapper::toDomainModel);
    }

    @Override
    public List<Pet> findByOwnerId(OwnerId ownerId) {
        return jpaRepository.findByOwnerId(ownerId.getValue())
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pet> findActiveByOwnerId(OwnerId ownerId) {
        return jpaRepository.findActiveByOwnerId(ownerId.getValue())
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pet> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(PetId id) {
        jpaRepository.deleteById(id.getValue());
    }

    @Override
    public boolean existsByNameAndOwnerId(String name, OwnerId ownerId) {
        return jpaRepository.existsByNameAndOwnerId(name, ownerId.getValue());
    }
}