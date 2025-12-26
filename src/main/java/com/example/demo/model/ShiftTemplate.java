package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(
    name = "shift_template",
    uniqueConstraints = @UniqueConstraint(columnNames = {"templateName", "department_id"})
)
public class ShiftTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;
    private LocalTime startTime;
    private LocalTime endTime;
    private String requiredSkills;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public ShiftTemplate() {}

    // REQUIRED BY TESTS
    public ShiftTemplate(String name, LocalTime start, LocalTime end, String skills, Department dept) {
        this.templateName = name;
        this.startTime = start;
        this.endTime = end;
        this.requiredSkills = skills;
        this.department = dept;
    }

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
