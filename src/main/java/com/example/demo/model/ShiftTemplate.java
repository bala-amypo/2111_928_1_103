package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalTime;
import java.util.List;

@Entity
public class ShiftTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String templateName;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotBlank
    private String requiredSkills;

    @ManyToOne
    @NotNull
    private Department department;

    @OneToMany(mappedBy = "shiftTemplate", cascade = CascadeType.ALL)
    private List<GeneratedShiftSchedule> schedules;

    // getters & setters
}
