package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "generated_shift_schedule")
public class GeneratedShiftSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Shift date is required")
    @Column(nullable = false)
    private LocalDate shiftDate;

    @NotNull(message = "Start time is required")
    @Column(nullable = false)
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "shift_template_id", nullable = false)
    private ShiftTemplate shiftTemplate;

    
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public GeneratedShiftSchedule() {}

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public LocalDate getShiftDate() {
        return shiftDate;
    }
 
    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
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
 
    public ShiftTemplate getShiftTemplate() {
        return shiftTemplate;
    }
 
    public void setShiftTemplate(ShiftTemplate shiftTemplate) {
        this.shiftTemplate = shiftTemplate;
    }
 
    public Department getDepartment() {
        return department;
    }
 
    public void setDepartment(Department department) {
        this.department = department;
    }
 
    public Employee getEmployee() {
        return employee;
    }
 
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
