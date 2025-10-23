package com.veterinary.clinic.domain.model.valueobjects;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    UNKNOWN("Unknown");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Gender fromString(String text) {
        if (text == null) {
            return UNKNOWN;
        }

        switch (text.toUpperCase()) {
            case "MALE":
            case "M":
                return MALE;
            case "FEMALE":
            case "F":
                return FEMALE;
            default:
                return UNKNOWN;
        }
    }
}