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
    private final DepartmentRepository departmentRepository;

    // ✅ TEST-EXPECTED CONSTRUCTOR
    public ShiftTemplateServiceImpl(
            ShiftTemplateRepository shiftTemplateRepository,
            DepartmentRepository departmentRepository
    ) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate template) {
        return shiftTemplateRepository.save(template);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        return shiftTemplateRepository.findByDepartment_Id(departmentId);
    }

    @Override
    public ShiftTemplate getById(Long id) {
        return shiftTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShiftTemplate not found"));
    }

    @Override
    public List<ShiftTemplate> getAll() {
        return shiftTemplateRepository.findAll();
    }
}
