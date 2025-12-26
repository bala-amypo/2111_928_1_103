package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "department", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String requiredSkills;

    public Department() {}

    // REQUIRED BY TESTS
    public Department(String name, String description, String requiredSkills) {
        this.name = name;
        this.description = description;
        this.requiredSkills = requiredSkills;
    }

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }
}
