package com.veterinary.clinic.infrastructure.adapter.out.persistence.mapper;

import com.veterinary.clinic.domain.model.Owner;
import com.veterinary.clinic.domain.model.valueobjects.OwnerId;
import com.veterinary.clinic.domain.model.valueobjects.Email;
import com.veterinary.clinic.infrastructure.adapter.out.persistence.jpa.entity.OwnerJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class OwnerJpaMapper {

    public OwnerJpaEntity toJpaEntity(Owner owner) {
        OwnerJpaEntity entity = new OwnerJpaEntity();
        entity.setId(owner.getId().getValue());
        entity.setFirstName(owner.getFirstName());
        entity.setLastName(owner.getLastName());
        entity.setEmail(owner.getEmail().getValue());
        entity.setPhone(owner.getPhone());
        entity.setAddress(owner.getAddress());
        return entity;
    }

    public Owner toDomainModel(OwnerJpaEntity entity) {
        return new Owner(
                new OwnerId(entity.getId()),
                entity.getFirstName(),
                entity.getLastName(),
                new Email(entity.getEmail()),
                entity.getPhone(),
                entity.getAddress()
        );
    }
}