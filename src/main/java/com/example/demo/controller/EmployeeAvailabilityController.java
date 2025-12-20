package com.example.demo.controller;

import com.example.demo.entity.EmployeeAvailability;
import com.example.demo.service.AvailabilityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
@Tag(name = "Employee Availability Endpoints")
public class EmployeeAvailabilityController {

    private final AvailabilityService availabilityService;

    public EmployeeAvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeAvailability create(@RequestBody EmployeeAvailability availability) {
        return availabilityService.create(availability);
    }

    @GetMapping("/availability/{id}")
    public EmployeeAvailability get(@PathVariable Long id) {
        return availabilityService.update(id, null);
    }

    @GetMapping("/date/{date}")
    public List<EmployeeAvailability> getByDate(@PathVariable LocalDate date) {
        return availabilityService.getByDate(date);
    }
}
