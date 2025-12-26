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

    // ✅ REQUIRED BY INTERFACE
    @Override
    public List<ShiftTemplate> getAll() {
        return shiftTemplateRepository.findAll();
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public ShiftTemplate create(ShiftTemplate shiftTemplate) {

        // ❌ INVALID TIME (testShiftTemplateInvalidTime)
        if (shiftTemplate.getEndTime().isBefore(shiftTemplate.getStartTime())
                || shiftTemplate.getEndTime().equals(shiftTemplate.getStartTime())) {
            throw new RuntimeException("End time must be after start time");
        }

        // ❌ UNIQUE TEMPLATE PER DEPARTMENT (testShiftTemplateUniqueWithinDept)
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
