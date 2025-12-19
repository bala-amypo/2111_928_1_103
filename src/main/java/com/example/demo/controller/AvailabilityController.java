package com.example.demo.controller;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.service.AvailabilityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<?> create(
            @PathVariable Long employeeId,
            @Valid @RequestBody EmployeeAvailability availability,
            BindingResult result) {

        // 🔴 Validation check
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().get(0).getDefaultMessage()
            );
        }

        return ResponseEntity.ok(
                availabilityService.create(employeeId, availability)
        );
    }

    @GetMapping("/employee/{employeeId}")
    public List<EmployeeAvailability> getByEmployee(@PathVariable Long employeeId) {
        return availabilityService.getByEmployee(employeeId);
    }

    @GetMapping("/date/{date}")
    public List<EmployeeAvailability> getByDate(@PathVariable String date) {
        return availabilityService.getByDate(LocalDate.parse(date));
    }
}
