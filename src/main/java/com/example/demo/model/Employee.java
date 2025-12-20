package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String fullName;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String role;

    @NotBlank
    private String skills;

    @NotNull
    @Min(1)
    @Max(80)
    private Integer maxWeeklyHours;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeAvailability> availabilityList;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<GeneratedShiftSchedule> schedules;

    // getters & setters (unchanged)
}
