package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shifts")
public class ShiftTemplateController {

    private final ShiftTemplateService shiftTemplateService;

    public ShiftTemplateController(ShiftTemplateService shiftTemplateService) {
        this.shiftTemplateService = shiftTemplateService;
    }

    @PostMapping("/{departmentId}")
    public ShiftTemplate create(@PathVariable Long departmentId,
                                @RequestBody ShiftTemplate template) {
        return shiftTemplateService.create(template, departmentId);
    }

    @GetMapping("/{id}")
    public ShiftTemplate get(@PathVariable Long id) {
        return shiftTemplateService.get(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<ShiftTemplate> getByDepartment(@PathVariable Long departmentId) {
        return shiftTemplateService.getByDepartment(departmentId);
    }
}
