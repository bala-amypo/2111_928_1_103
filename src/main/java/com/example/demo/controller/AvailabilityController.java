package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;
    private final EmployeeRepository employeeRepository;

    public AvailabilityController(
            AvailabilityService availabilityService,
            EmployeeRepository employeeRepository) {
        this.availabilityService = availabilityService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeAvailability> create(
            @PathVariable Long employeeId,
            @RequestBody EmployeeAvailability availability) {

        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        availability.setEmployee(emp);
        return ResponseEntity.ok(availabilityService.create(availability));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<EmployeeAvailability>> byDate(@PathVariable String date) {
        return ResponseEntity.ok(
                availabilityService.getByDate(LocalDate.parse(date))
        );
    }
}
