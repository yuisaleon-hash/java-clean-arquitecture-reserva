package com.veterinary.clinic.application.dto.response;

import java.time.LocalDate;
import java.math.BigDecimal;

public class PetResponse {
    private String id;
    private String name;
    private String speciesName;
    private String breed;
    private LocalDate birthDate;
    private Integer ageInYears;
    private BigDecimal weight;
    private String color;
    private String gender;
    private String ownerId;
    private String ownerName;
    private boolean active;

    // Constructor por defecto
    public PetResponse() {}

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public Integer getAgeInYears() { return ageInYears; }
    public void setAgeInYears(Integer ageInYears) { this.ageInYears = ageInYears; }

    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}