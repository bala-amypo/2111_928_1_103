package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "employee",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee name is required")
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Role is required")
    @Column(nullable = false)
    private String role = "STAFF";

    @NotBlank(message = "Skills are required")
    @Column(nullable = false)
    private String skills; 

    @Min(value = 1, message = "Max weekly hours must be greater than 0")
    @Column(nullable = false)
    private Integer maxWeeklyHours;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

   
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeAvailability> availabilities;

   
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<GeneratedShiftSchedule> schedules;

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

    public List<EmployeeAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<EmployeeAvailability> availabilities) {
        this.availabilities = availabilities;
    }

    public List<GeneratedShiftSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<GeneratedShiftSchedule> schedules) {
        this.schedules = schedules;
    }
}
