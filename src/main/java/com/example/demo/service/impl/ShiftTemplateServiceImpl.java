package com.example.demo.service.impl;

import com.example.demo.entity.Department;
import com.example.demo.entity.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository templateRepository;
    private final DepartmentRepository departmentRepository;

    public ShiftTemplateServiceImpl(
            ShiftTemplateRepository templateRepository,
            DepartmentRepository departmentRepository
    ) {
        this.templateRepository = templateRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate template, Long departmentId) {

        if (template.getEndTime().isBefore(template.getStartTime())) {
            throw new RuntimeException("End time must be after start time");
        }

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        template.setDepartment(department); // 🔥 CRITICAL FIX

        return templateRepository.save(template);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return templateRepository.findByDepartment(department);
    }

    @Override
    public ShiftTemplate getById(Long id) {
        return templateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShiftTemplate not found"));
    }
}
