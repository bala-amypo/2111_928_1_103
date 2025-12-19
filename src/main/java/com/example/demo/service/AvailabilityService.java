package com.example.demo.service;

import com.example.demo.model.EmployeeAvailability;
import java.time.LocalDate;
import java.util.List;

public interface AvailabilityService {
    EmployeeAvailability create(Long employeeId, EmployeeAvailability availability);
    EmployeeAvailability get(Long id);
    List<EmployeeAvailability> getByEmployee(Long employeeId);
    List<EmployeeAvailability> getByDate(LocalDate date);
}
