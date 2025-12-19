package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee create(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable @Positive(message = "Employee ID must be positive") Long id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive(message = "Employee ID must be positive") Long id) {
        employeeService.deleteEmployee(id);
    }
}
