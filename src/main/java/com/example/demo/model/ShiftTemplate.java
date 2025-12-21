package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
@Table(
        name = "shift_template",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"templateName", "department_id"})
        }
)
public class ShiftTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Template name is required")
    @Column(nullable = false)
    private String templateName;

    @NotNull(message = "Start time is required")
    @Column(nullable = false)
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    @Column(nullable = false)
    private LocalTime endTime;

    @NotBlank(message = "Required skills are mandatory")
    @Column(nullable = false)
    private String requiredSkills;

   
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public ShiftTemplate() {}

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getTemplateName() {
        return templateName;
    }
 
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
 
    public LocalTime getStartTime() {
        return startTime;
    }
 
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
 
    public LocalTime getEndTime() {
        return endTime;
    }
 
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
 
    public String getRequiredSkills() {
        return requiredSkills;
    }
 
    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
 
    public Department getDepartment() {
        return department;
    }
 
    public void setDepartment(Department department) {
        this.department = department;
    }
}
