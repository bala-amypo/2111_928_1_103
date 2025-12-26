package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class DepartmentDto {

    @NotBlank(message = "Department name is required")
    private String name;

    private String description;

    @NotBlank(message = "Required skills are mandatory")
    private String requiredSkills;

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
}
