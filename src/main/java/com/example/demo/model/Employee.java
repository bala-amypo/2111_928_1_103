package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "employee",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String role = "STAFF";

    @Column(nullable = false)
    private String skills; // comma-separated

    @Column(nullable = false)
    private Integer maxWeeklyHours;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Employee() {}

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getFullName() {
        return fullName;
    }
 
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
 
    public String getSkills() {
        return skills;
    }
 
    public void setSkills(String skills) {
        this.skills = skills;
    }
 
    public Integer getMaxWeeklyHours() {
        return maxWeeklyHours;
    }
 
    public void setMaxWeeklyHours(Integer maxWeeklyHours) {
        this.maxWeeklyHours = maxWeeklyHours;
    }
 
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
