package com.veterinary.clinic.domain.model;

import com.veterinary.clinic.domain.model.valueobjects.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Pet {
    private final PetId id;
    private String name;
    private final Species species;
    private String breed;
    private LocalDate birthDate;
    private Weight weight;
    private String color;
    private Gender gender;
    private final OwnerId ownerId;
    private boolean active;

    public Pet(PetId id, String name, Species species, String breed,
               LocalDate birthDate, Weight weight, String color,
               Gender gender, OwnerId ownerId) {
        this.id = Objects.requireNonNull(id, "Pet ID cannot be null");
        this.ownerId = Objects.requireNonNull(ownerId, "Owner ID cannot be null");
        this.species = Objects.requireNonNull(species, "Species cannot be null");
        this.setName(name);
        this.breed = breed;
        this.birthDate = birthDate;
        this.weight = weight;
        this.color = color;
        this.gender = gender;
        this.active = true;
    }

    // Constructor para crear nueva mascota
    public static Pet create(String name, Species species, String breed,
                             LocalDate birthDate, Weight weight, String color,
                             Gender gender, OwnerId ownerId) {
        return new Pet(PetId.generate(), name, species, breed, birthDate,
                weight, color, gender, ownerId);
    }

    public void updateWeight(Weight newWeight) {
        if (newWeight == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }
        this.weight = newWeight;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Pet name cannot be null or empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Pet name cannot exceed 100 characters");
        }
        this.name = name.trim();
    }

    public int getAgeInYears() {
        if (birthDate == null) {
            return 0;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public boolean isAdult() {
        return getAgeInYears() >= 1;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    // Getters
    public PetId getId() { return id; }
    public String getName() { return name; }
    public Species getSpecies() { return species; }
    public String getBreed() { return breed; }
    public LocalDate getBirthDate() { return birthDate; }
    public Weight getWeight() { return weight; }
    public String getColor() { return color; }
    public Gender getGender() { return gender; }
    public OwnerId getOwnerId() { return ownerId; }
    public boolean isActive() { return active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}