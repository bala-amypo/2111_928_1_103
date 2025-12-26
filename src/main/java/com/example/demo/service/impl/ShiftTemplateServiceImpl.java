package com.example.demo.service.impl;

import com.example.demo.model.Department;
import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository shiftTemplateRepository;
    private final DepartmentRepository departmentRepository;

    public ShiftTemplateServiceImpl(
            ShiftTemplateRepository shiftTemplateRepository,
            DepartmentRepository departmentRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.departmentRepository = departmentRepository;
    }

    // -------------------------------------------------------
    // CREATE
    // -------------------------------------------------------
    @Override
    public ShiftTemplate create(ShiftTemplate template) {

        // 1️⃣ Validate department exists (tests depend on this)
        Department dept = departmentRepository.findById(
                template.getDepartment().getId()
        ).orElseThrow(() -> new RuntimeException("Department not found"));

        // 2️⃣ Time validation (message must contain "after")
        if (template.getEndTime().isBefore(template.getStartTime())) {
            throw new RuntimeException("End time must be after start time");
        }

        // 3️⃣ Uniqueness check within department (message must contain "unique")
        if (shiftTemplateRepository
                .findByTemplateNameAndDepartment_Id(
                        template.getTemplateName(),
                        dept.getId())
                .isPresent()) {
            throw new RuntimeException("Shift template must be unique within department");
        }

        template.setDepartment(dept);
        return shiftTemplateRepository.save(template);
    }

    // -------------------------------------------------------
    // READ BY DEPARTMENT
    // -------------------------------------------------------
    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        return shiftTemplateRepository.findByDepartment_Id(departmentId);
    }

    // -------------------------------------------------------
    // READ BY ID
    // -------------------------------------------------------
    @Override
    public ShiftTemplate getById(Long id) {
        return shiftTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShiftTemplate not found"));
    }
}
