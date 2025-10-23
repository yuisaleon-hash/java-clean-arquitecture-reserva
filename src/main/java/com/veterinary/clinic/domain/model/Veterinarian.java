package com.veterinary.clinic.domain.model;

import com.veterinary.clinic.domain.model.valueobjects.VeterinarianId;
import com.veterinary.clinic.domain.model.valueobjects.Email;
import java.util.Objects;

public class Veterinarian {
    private final VeterinarianId id;
    private String firstName;
    private String lastName;
    private Email email;
    private String phone;
    private String licenseNumber;
    private String specialization;
    private boolean active;

    public Veterinarian(VeterinarianId id, String firstName, String lastName, Email email,
                        String phone, String licenseNumber, String specialization) {
        this.id = Objects.requireNonNull(id, "Veterinarian ID cannot be null");
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.phone = phone;
        this.setLicenseNumber(licenseNumber);
        this.specialization = specialization;
        this.active = true;
    }

    public static Veterinarian create(String firstName, String lastName, Email email,
                                      String phone, String licenseNumber, String specialization) {
        return new Veterinarian(VeterinarianId.generate(), firstName, lastName, email,
                phone, licenseNumber, specialization);
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName.trim();
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.lastName = lastName.trim();
    }

    public void setLicenseNumber(String licenseNumber) {
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("License number cannot be null or empty");
        }
        this.licenseNumber = licenseNumber.trim();
    }

    public String getFullName() {
        return "Dr. " + firstName + " " + lastName;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    // Getters
    public VeterinarianId getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Email getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLicenseNumber() { return licenseNumber; }
    public String getSpecialization() { return specialization; }
    public boolean isActive() { return active; }

    // Setters
    public void setEmail(Email email) {
        this.email = Objects.requireNonNull(email, "Email cannot be null");
    }
    public void setPhone(String phone) { this.phone = phone; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veterinarian)) return false;
        Veterinarian that = (Veterinarian) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}