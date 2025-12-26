package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EmployeeDto {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    private String role;

    @NotBlank(message = "Skills are required")
    private String skills;

    @NotNull(message = "Max weekly hours required")
    @Positive(message = "Hours must be positive")
    private Integer maxWeeklyHours;

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
}
