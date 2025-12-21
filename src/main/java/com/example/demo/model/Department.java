package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "department",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @NotBlank(message = "Required skills are mandatory")
    @Column(nullable = false)
    private String requiredSkills;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<ShiftTemplate> shiftTemplates;

    public Department() {}

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getRequiredSkills() {
        return requiredSkills;
    }
 
    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
 
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ShiftTemplate> getShiftTemplates() {
        return shiftTemplates;
    }

    public void setShiftTemplates(List<ShiftTemplate> shiftTemplates) {
        this.shiftTemplates = shiftTemplates;
    }
}
