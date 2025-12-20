package com.example.demo.repository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeAvailabilityRepository
        extends JpaRepository<EmployeeAvailability, Long> {

    Optional<EmployeeAvailability> findByEmployeeAndAvailableDate(
            Employee employee,
            LocalDate availableDate
    );

    List<EmployeeAvailability> findByAvailableDateAndAvailableTrue(
            LocalDate availableDate
    );
}
