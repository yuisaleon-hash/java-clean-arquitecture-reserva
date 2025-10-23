package com.veterinary.clinic.infrastructure.adapter.out.persistence.adapter;

import com.veterinary.clinic.domain.model.Owner;
import com.veterinary.clinic.domain.model.valueobjects.OwnerId;
import com.veterinary.clinic.domain.model.valueobjects.Email;
import com.veterinary.clinic.domain.repository.OwnerRepository;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.repository.OwnerJpaRepository;
import com.veterinary.clinic.shared.annotation.Adapter;

import java.util.List;
import java.util.Optional;

@Adapter
public class OwnerRepositoryAdapter implements OwnerRepository {

    private final OwnerJpaRepository jpaRepository;

    public OwnerRepositoryAdapter(OwnerJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Owner save(Owner owner) {
        // Implementación básica - necesitarías mapper completo
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Optional<Owner> findById(OwnerId id) {
        // Implementación básica
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Optional<Owner> findByEmail(Email email) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Owner> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void delete(OwnerId id) {
        jpaRepository.deleteById(id.getValue());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.existsByEmail(email.getValue());
    }
}