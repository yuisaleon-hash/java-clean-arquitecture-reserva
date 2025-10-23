package com.veterinary.clinic.domain.exception;

public class PetNotFoundException extends DomainException {

    public PetNotFoundException(String message) {
        super(message);
    }
/*
    public PetNotFoundException(String petId) {
        super("Pet not found with ID: " + petId);
    }

 */
}