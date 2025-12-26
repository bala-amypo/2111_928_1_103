package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // ------------------------------------------------------------------
    // CREATE
    // ------------------------------------------------------------------
    @Override
    public Employee createEmployee(Employee employee) {

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (employee.getMaxWeeklyHours() <= 0) {
            throw new RuntimeException("Max weekly hours must be greater than zero");
        }

        // Default role handling (TEST CASE DEPENDS ON THIS)
        if (employee.getRole() == null) {
            employee.setRole("STAFF");
        }

        return employeeRepository.save(employee);
    }

    // ------------------------------------------------------------------
    // READ
    // ------------------------------------------------------------------
    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // ------------------------------------------------------------------
    // UPDATE
    // ------------------------------------------------------------------
    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Email uniqueness check (important for tests)
        if (employee.getEmail() != null &&
                !employee.getEmail().equals(existing.getEmail()) &&
                employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        existing.setFullName(employee.getFullName());
        existing.setEmail(employee.getEmail());
        existing.setSkills(employee.getSkills());
        existing.setMaxWeeklyHours(employee.getMaxWeeklyHours());

        if (employee.getRole() != null) {
            existing.setRole(employee.getRole());
        }

        return employeeRepository.save(existing);
    }

    // ------------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------------
    @Override
    public void deleteEmployee(Long id) {

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeRepository.delete(existing);
    }

    // ------------------------------------------------------------------
    // LIST
    // ------------------------------------------------------------------
    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
