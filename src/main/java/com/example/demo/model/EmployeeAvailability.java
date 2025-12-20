package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(
        name = "employee_availability",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"employee_id", "availableDate"})
        }
)
public class EmployeeAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Many Availability → One Employee
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull(message = "Available date is required")
    @Column(nullable = false)
    private LocalDate availableDate;

    @NotNull(message = "Availability status is required")
    @Column(nullable = false)
    private Boolean available;

    public EmployeeAvailability() {}

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public Employee getEmployee() {
        return employee;
    }
 
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
 
    public LocalDate getAvailableDate() {
        return availableDate;
    }
 
    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }
 
    public Boolean getAvailable() {
        return available;
    }
 
    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
