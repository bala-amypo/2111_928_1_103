package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository shiftTemplateRepository;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftTemplateRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate template, Long departmentId) {
        // Set departmentId directly
        template.setDepartmentId(departmentId);
        return shiftTemplateRepository.save(template);
    }

    @Override
    public ShiftTemplate get(Long id) {
        return shiftTemplateRepository.findById(id).orElse(null);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        return shiftTemplateRepository.findByDepartmentId(departmentId);
    }
}
