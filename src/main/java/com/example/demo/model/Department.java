package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Department {

    @Id
     @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<ShiftTemplate> shiftTemplates;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<GeneratedShiftSchedule> schedules;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<ShiftTemplate> getShiftTemplates() { return shiftTemplates; }
    public void setShiftTemplates(List<ShiftTemplate> shiftTemplates) {
        this.shiftTemplates = shiftTemplates;
    }

    public List<GeneratedShiftSchedule> getSchedules() { return schedules; }
    public void setSchedules(List<GeneratedShiftSchedule> schedules) {
        this.schedules = schedules;
    }
}
