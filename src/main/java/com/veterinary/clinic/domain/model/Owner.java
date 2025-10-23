package com.veterinary.clinic.domain.model;

import com.veterinary.clinic.domain.model.valueobjects.OwnerId;
import com.veterinary.clinic.domain.model.valueobjects.Email;
import java.util.Objects;

public class Owner {
    private final OwnerId id;
    private String firstName;
    private String lastName;
    private Email email;
    private String phone;
    private String address;

    public Owner(OwnerId id, String firstName, String lastName, Email email, String phone, String address) {
        this.id = Objects.requireNonNull(id, "Owner ID cannot be null");
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.phone = phone;
        this.address = address;
    }

    public static Owner create(String firstName, String lastName, Email email, String phone, String address) {
        return new Owner(OwnerId.generate(), firstName, lastName, email, phone, address);
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

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Getters
    public OwnerId getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Email getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    // Setters
    public void setEmail(Email email) {
        this.email = Objects.requireNonNull(email, "Email cannot be null");
    }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}