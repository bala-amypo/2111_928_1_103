package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "generated_shift_schedule")
public class GeneratedShiftSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate shiftDate;

    @Column(nullable = false)
    private LocalTime startTime;

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
