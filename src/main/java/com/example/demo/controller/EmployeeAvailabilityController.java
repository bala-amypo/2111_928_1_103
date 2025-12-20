package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeAvailability;
import com.example.demo.service.AvailabilityService;
import com.example.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
@Tag(name = "Employee Availability Endpoints")
public class EmployeeAvailabilityController {

    private final AvailabilityService availabilityService;
    private final EmployeeService employeeService;

    public EmployeeAvailabilityController(
            AvailabilityService availabilityService,
            EmployeeService employeeService) {
        this.availabilityService = availabilityService;
        this.employeeService = employeeService;
    }

    // ✅ CREATE availability for an employee
    @PostMapping("/employee/{employeeId}")
    public EmployeeAvailability create(
            @PathVariable Long employeeId,
            @RequestBody EmployeeAvailability availability) {

        Employee employee = employeeService.getEmployee(employeeId);
        availability.setEmployee(employee);

        return availabilityService.create(availability);
    }

    // ✅ GET availability by date (required by spec)
    @GetMapping("/date/{date}")
    public List<EmployeeAvailability> getByDate(@PathVariable LocalDate date) {
        return availabilityService.getByDate(date);
    }
}
