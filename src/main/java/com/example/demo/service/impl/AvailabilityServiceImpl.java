package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final EmployeeRepository employeeRepository;

    public AvailabilityServiceImpl(
            AvailabilityRepository availabilityRepository,
            EmployeeRepository employeeRepository) {
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = employeeRepository;
    }

    // -------------------------------------------------------
    // CREATE
    // -------------------------------------------------------
    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {

        Long employeeId = availability.getEmployee().getId();
        LocalDate date = availability.getAvailableDate();

        // Unique availability check (TEST EXPECTS "exists")
        if (availabilityRepository
                .findByEmployee_IdAndAvailableDate(employeeId, date)
                .isPresent()) {
            throw new RuntimeException("Availability already exists");
        }

        return availabilityRepository.save(availability);
    }

    // -------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------
    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability availability) {

        EmployeeAvailability existing = availabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        existing.setAvailable(availability.getAvailable());
        existing.setAvailableDate(availability.getAvailableDate());

        return availabilityRepository.save(existing);
    }

    // -------------------------------------------------------
    // DELETE
    // -------------------------------------------------------
    @Override
    public void delete(Long id) {

        EmployeeAvailability existing = availabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        availabilityRepository.delete(existing);
    }

    // -------------------------------------------------------
    // READ BY DATE
    // -------------------------------------------------------
    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return availabilityRepository.findByAvailableDateAndAvailable(date, true);
    }
}
