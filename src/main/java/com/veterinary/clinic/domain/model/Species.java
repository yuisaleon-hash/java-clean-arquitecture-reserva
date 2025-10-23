package com.veterinary.clinic.domain.model;

import com.veterinary.clinic.domain.model.valueobjects.SpeciesId;
import java.util.Objects;

public class Species {
    private final SpeciesId id;
    private String name;
    private String description;

    public Species(SpeciesId id, String name, String description) {
        this.id = Objects.requireNonNull(id, "Species ID cannot be null");
        this.setName(name);
        this.description = description;
    }

    public static Species create(String name, String description) {
        return new Species(SpeciesId.generate(), name, description);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Species name cannot be null or empty");
        }
        this.name = name.trim();
    }

    // Getters
    public SpeciesId getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Species)) return false;
        Species species = (Species) o;
        return Objects.equals(id, species.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}