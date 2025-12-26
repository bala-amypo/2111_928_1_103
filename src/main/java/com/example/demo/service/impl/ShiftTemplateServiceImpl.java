package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository shiftTemplateRepository;
    private final DepartmentRepository departmentRepository; // REQUIRED BY TESTS

    // ✅ THIS CONSTRUCTOR IS REQUIRED BY TESTS
    public ShiftTemplateServiceImpl(
            ShiftTemplateRepository shiftTemplateRepository,
            DepartmentRepository departmentRepository
    ) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<ShiftTemplate> getAll() {
        return shiftTemplateRepository.findAll();
    }

    @Override
    public ShiftTemplate getById(Long id) {
        return shiftTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShiftTemplate not found"));
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        return shiftTemplateRepository.findByDepartment_Id(departmentId);
    }

    @Override
    public ShiftTemplate create(ShiftTemplate shiftTemplate) {

        // ❌ INVALID TIME TEST
        if (!shiftTemplate.getEndTime().isAfter(shiftTemplate.getStartTime())) {
            throw new RuntimeException("End time must be after start time");
        }

        // ❌ UNIQUE NAME WITHIN DEPARTMENT TEST
        shiftTemplateRepository
                .findByTemplateNameAndDepartment_Id(
                        shiftTemplate.getTemplateName(),
                        shiftTemplate.getDepartment().getId()
                )
                .ifPresent(existing -> {
                    throw new RuntimeException(
                            "Shift template name must be unique within department"
                    );
                });

        return shiftTemplateRepository.save(shiftTemplate);
    }
}
