package com.example.demo.repository;

import com.example.demo.entity.Department;
import com.example.demo.entity.ShiftTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {

    List<ShiftTemplate> findByDepartment(Department department);

    Optional<ShiftTemplate> findByTemplateNameAndDepartment(
            String templateName,
            Department department
    );

    boolean existsByTemplateNameAndDepartment(
            String templateName,
            Department department
    );
}
