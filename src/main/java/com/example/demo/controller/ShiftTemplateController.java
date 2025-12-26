package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.service.ShiftTemplateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    // ✅ REQUIRED by test cases (NO arguments)
    @GetMapping
    public List<ShiftTemplate> list() {
        return shiftTemplateService.getAll();
    }

    @PostMapping("/department/{departmentId}")
    public ShiftTemplate createTemplate(
            @PathVariable Long departmentId,
            @Valid @RequestBody ShiftTemplate template
    ) {
        return shiftTemplateService.create(template, departmentId);
    }

    @GetMapping("/department/{departmentId}")
    public List<ShiftTemplate> getTemplatesByDepartment(
            @PathVariable Long departmentId
    ) {
        return shiftTemplateService.getByDepartment(departmentId);
    }

    @GetMapping("/{id}")
    public ShiftTemplate getTemplate(@PathVariable Long id) {
        return shiftTemplateService.getById(id);
    }
}
