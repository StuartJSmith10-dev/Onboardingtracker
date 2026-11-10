package com.example.onboarding_tracker.jpa_objects;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity 
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required and cannot be blank")
    private String name;

    @NotBlank(message = "Role is required and cannot be blank")
    private String role;

    @NotNull(message = "Onboarding date is required")
    private LocalDate onboardingDate; // Automatically serialized/deserialized as yyyy-MM-dd

    // Constructors
    public Employee() {}

    public Employee(String name, String role, LocalDate onboardingDate) {
        this.name = name;
        this.role = role;
        this.onboardingDate = onboardingDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public LocalDate getOnboardingDate() { return onboardingDate; }
    public void setOnboardingDate(LocalDate onboardingDate) { this.onboardingDate = onboardingDate; }
}
