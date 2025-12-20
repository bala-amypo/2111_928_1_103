package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public EmployeeAvailability create(Long employeeId, EmployeeAvailability availability) {
        // Set employeeId directly
        availability.setEmployeeId(employeeId);
        return availabilityRepository.save(availability);
    }

    @Override
    public EmployeeAvailability get(Long id) {
        return availabilityRepository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeAvailability> getByEmployee(Long employeeId) {
        return availabilityRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return availabilityRepository.findByAvailableDateAndAvailable(date, true);
    }
}
