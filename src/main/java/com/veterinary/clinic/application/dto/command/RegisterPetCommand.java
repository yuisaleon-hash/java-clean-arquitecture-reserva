package com.veterinary.clinic.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.math.BigDecimal;

public class RegisterPetCommand {

    @NotBlank(message = "Pet name is required")
    private String name;

    @NotNull(message = "Species ID is required")
    private String speciesId;

    private String breed;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @Positive(message = "Weight must be positive")
    private BigDecimal weight;

    private String color;

    private String gender;

    @NotNull(message = "Owner ID is required")
    private String ownerId;

    // Constructor por defecto
    public RegisterPetCommand() {}

    // Constructor completo
    public RegisterPetCommand(String name, String speciesId, String breed,
                              LocalDate birthDate, BigDecimal weight, String color,
                              String gender, String ownerId) {
        this.name = name;
        this.speciesId = speciesId;
        this.breed = breed;
        this.birthDate = birthDate;
        this.weight = weight;
        this.color = color;
        this.gender = gender;
        this.ownerId = ownerId;
    }

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpeciesId() { return speciesId; }
    public void setSpeciesId(String speciesId) { this.speciesId = speciesId; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
}