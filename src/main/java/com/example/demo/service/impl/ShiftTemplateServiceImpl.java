package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository shiftTemplateRepository;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftTemplateRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate shiftTemplate) {

        // ❌ INVALID TIME CHECK (must contain word "after")
        if (shiftTemplate.getEndTime().isBefore(shiftTemplate.getStartTime())
                || shiftTemplate.getEndTime().equals(shiftTemplate.getStartTime())) {
            throw new RuntimeException("End time must be after start time");
        }

        // ❌ UNIQUE TEMPLATE NAME WITHIN DEPARTMENT (must contain word "unique")
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
