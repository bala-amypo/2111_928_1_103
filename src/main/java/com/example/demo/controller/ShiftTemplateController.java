package com.example.demo.controller;

import com.example.demo.entity.ShiftTemplate;
import com.example.demo.service.ShiftTemplateService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ShiftTemplate create(@PathVariable Long departmentId,
                                @RequestBody ShiftTemplate template) {
        return shiftTemplateService.create(template);
    }

    @GetMapping("/department/{departmentId}")
    public List<ShiftTemplate> getByDepartment(@PathVariable Long departmentId) {
        return shiftTemplateService.getByDepartment(departmentId);
    }

    @GetMapping("/{id}")
    public ShiftTemplate get(@PathVariable Long id) {
        return null; // optional, not required by STEP-5
    }
}
