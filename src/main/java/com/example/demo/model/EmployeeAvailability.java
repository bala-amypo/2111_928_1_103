package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
    name = "employee_availability",
    uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "availableDate"})
)
public class EmployeeAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    private LocalDate availableDate;
    private boolean available;

    public EmployeeAvailability() {}

    // REQUIRED BY TESTS
    public EmployeeAvailability(Employee employee, LocalDate date, boolean available) {
        this.employee = employee;
        this.availableDate = date;
        this.available = available;
    }

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public LocalDate getAvailableDate() { return availableDate; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }

    public boolean getAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
