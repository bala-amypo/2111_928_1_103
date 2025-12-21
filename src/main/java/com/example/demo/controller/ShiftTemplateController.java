package com.example.demo.controller;

import com.example.demo.entity.ShiftTemplate;
import com.example.demo.service.ShiftTemplateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
@Tag(name = "Shift Templates Endpoints")
public class ShiftTemplateController {

    private final ShiftTemplateService shiftTemplateService;

    public ShiftTemplateController(ShiftTemplateService shiftTemplateService) {
        this.shiftTemplateService = shiftTemplateService;
    }

    @PostMapping("/department/{departmentId}")
    public ShiftTemplate createTemplate(
            @NotNull @Positive @PathVariable Long departmentId,
            @Valid @RequestBody ShiftTemplate template
    ) {
        return shiftTemplateService.create(template, departmentId);
    }

    @GetMapping("/department/{departmentId}")
    public List<ShiftTemplate> getTemplatesByDepartment(
            @NotNull @Positive @PathVariable Long departmentId
    ) {
        return shiftTemplateService.getByDepartment(departmentId);
    }

    @GetMapping("/{id}")
    public ShiftTemplate getTemplate(
            @NotNull @Positive @PathVariable Long id
    ) {
        return shiftTemplateService.getById(id);
    }
}
