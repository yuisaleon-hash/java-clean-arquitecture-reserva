package com.veterinary.clinic.infrastructure.adapter.out.persistence.adapter;

import com.veterinary.clinic.domain.model.Veterinarian;
import com.veterinary.clinic.domain.model.valueobjects.VeterinarianId;
import com.veterinary.clinic.domain.model.valueobjects.Email;
import com.veterinary.clinic.domain.repository.VeterinarianRepository;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.VeterinarianJpaEntity;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository.VeterinarianJpaRepository;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.mapper.VeterinarianJpaMapper;
import com.veterinary.clinic.shared.annotation.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class VeterinarianRepositoryAdapter implements VeterinarianRepository {

    private final VeterinarianJpaRepository jpaRepository;
    private final VeterinarianJpaMapper mapper;

    public VeterinarianRepositoryAdapter(VeterinarianJpaRepository jpaRepository, VeterinarianJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Veterinarian save(Veterinarian veterinarian) {
        VeterinarianJpaEntity entity = mapper.toJpaEntity(veterinarian);
        VeterinarianJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomainModel(savedEntity);
    }

    @Override
    public Optional<Veterinarian> findById(VeterinarianId id) {
        return jpaRepository.findById(id.getValue())
                .map(mapper::toDomainModel);
    }

    @Override
    public Optional<Veterinarian> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.getValue())
                .map(mapper::toDomainModel);
    }

    @Override
    public Optional<Veterinarian> findByLicenseNumber(String licenseNumber) {
        return jpaRepository.findByLicenseNumber(licenseNumber)
                .map(mapper::toDomainModel);
    }

    @Override
    public List<Veterinarian> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Veterinarian> findActiveVeterinarians() {
        return jpaRepository.findActiveVeterinarians()
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(VeterinarianId id) {
        jpaRepository.deleteById(id.getValue());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.existsByEmail(email.getValue());
    }

    @Override
    public boolean existsByLicenseNumber(String licenseNumber) {
        return jpaRepository.existsByLicenseNumber(licenseNumber);
    }
}