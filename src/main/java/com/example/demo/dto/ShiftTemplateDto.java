package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class ShiftTemplateDto {

    @NotBlank(message = "Template name required")
    private String templateName;

    @NotNull(message = "Start time required")
    private LocalTime startTime;

    @NotNull(message = "End time required")
    private LocalTime endTime;

    @NotBlank(message = "Required skills mandatory")
    private String requiredSkills;

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
}
