package com.veterinary.clinic.infrastructure.adapter.out.persistence.mapper;

import com.veterinary.clinic.domain.model.Veterinarian;
import com.veterinary.clinic.domain.model.valueobjects.VeterinarianId;
import com.veterinary.clinic.domain.model.valueobjects.Email;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.VeterinarianJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class VeterinarianJpaMapper {

    public VeterinarianJpaEntity toJpaEntity(Veterinarian veterinarian) {
        VeterinarianJpaEntity entity = new VeterinarianJpaEntity();
        entity.setId(veterinarian.getId().getValue());
        entity.setFirstName(veterinarian.getFirstName());
        entity.setLastName(veterinarian.getLastName());
        entity.setEmail(veterinarian.getEmail().getValue());
        entity.setPhone(veterinarian.getPhone());
        entity.setLicenseNumber(veterinarian.getLicenseNumber());
        entity.setSpecialization(veterinarian.getSpecialization());
        entity.setIsActive(veterinarian.isActive());
        return entity;
    }

    public Veterinarian toDomainModel(VeterinarianJpaEntity entity) {
        Veterinarian veterinarian = new Veterinarian(
                new VeterinarianId(entity.getId()),
                entity.getFirstName(),
                entity.getLastName(),
                new Email(entity.getEmail()),
                entity.getPhone(),
                entity.getLicenseNumber(),
                entity.getSpecialization()
        );

        if (!entity.getIsActive()) {
            veterinarian.deactivate();
        }

        return veterinarian;
    }
}