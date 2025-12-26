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

    // ✅ REQUIRED BY TESTS
    @Override
    public List<ShiftTemplate> getAll() {
        return shiftTemplateRepository.findAll();
    }

    // ✅ REQUIRED BY TESTS
    @Override
    public ShiftTemplate getById(Long id) {
        return shiftTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShiftTemplate not found"));
    }

    // ✅ REQUIRED BY TESTS
    @Override
    public ShiftTemplate create(ShiftTemplate shiftTemplate) {

        // ❌ INVALID TIME TEST
        if (!shiftTemplate.getEndTime().isAfter(shiftTemplate.getStartTime())) {
            throw new RuntimeException("End time must be after start time");
        }

        // ❌ UNIQUE WITHIN DEPARTMENT TEST
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
