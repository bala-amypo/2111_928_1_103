package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String role;

    private String skills;

    private int maxWeeklyHours;

    // REQUIRED BY JPA
    public Employee() {}

    // REQUIRED BY TESTS
    public Employee(String fullName, String email, String role, String skills, int maxWeeklyHours) {
        this.fullName = fullName;
        this.email = email;
        this.role = (role == null ? "STAFF" : role);
        this.skills = skills;
        this.maxWeeklyHours = maxWeeklyHours;
    }

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role == null ? "STAFF" : role; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public int getMaxWeeklyHours() { return maxWeeklyHours; }
    public void setMaxWeeklyHours(int maxWeeklyHours) { this.maxWeeklyHours = maxWeeklyHours; }
}
