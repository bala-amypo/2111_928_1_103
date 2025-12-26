package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class ShiftTemplateController {

    private final ShiftTemplateService shiftTemplateService;

    public ShiftTemplateController(ShiftTemplateService shiftTemplateService) {
        this.shiftTemplateService = shiftTemplateService;
    }

    // ✅ REQUIRED BY TEST (NO ARGUMENTS)
    @GetMapping
    public List<ShiftTemplate> list() {
        return shiftTemplateService.getAll();
    }

    @GetMapping("/department/{departmentId}")
    public List<ShiftTemplate> listByDepartment(@PathVariable Long departmentId) {
        return shiftTemplateService.getByDepartment(departmentId);
    }
}
