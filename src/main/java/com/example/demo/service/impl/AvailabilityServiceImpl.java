package com.example.demo.service.impl;

import com.example.demo.entity.EmployeeAvailability;
import com.example.demo.repository.EmployeeAvailabilityRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final EmployeeAvailabilityRepository repository;

    public AvailabilityServiceImpl(EmployeeAvailabilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {
        return repository.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability availability) {
        EmployeeAvailability existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        existing.setAvailable(availability.getAvailable());
        existing.setAvailableDate(availability.getAvailableDate());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return repository.findByAvailableDateAndAvailableTrue(date);
    }
}
