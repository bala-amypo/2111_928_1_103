package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class ShiftTemplateController {

    private final ShiftTemplateService shiftTemplateService;
    private final DepartmentRepository departmentRepository;

    // ✅ REQUIRED by TestNG (DO NOT CHANGE)
    public ShiftTemplateController(
            ShiftTemplateService shiftTemplateService,
            DepartmentRepository departmentRepository
    ) {
        this.shiftTemplateService = shiftTemplateService;
        this.departmentRepository = departmentRepository;
    }

    // ✅ REQUIRED for EMPTY DATABASE (Swagger POST)
    @PostMapping
    public ResponseEntity<ShiftTemplate> create(
            @RequestBody ShiftTemplate template
    ) {
        return ResponseEntity.ok(shiftTemplateService.create(template));
    }

    // ✅ REQUIRED BY TEST:
    // testShiftTemplateControllerList()
    @GetMapping
    public ResponseEntity<List<ShiftTemplate>> list() {
        return ResponseEntity.ok(shiftTemplateService.getAll());
    }

    // ✅ REQUIRED BY TEST:
    // testShiftTemplateQuery()
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<ShiftTemplate>> listByDepartment(
            @PathVariable Long departmentId
    ) {
        return ResponseEntity.ok(
                shiftTemplateService.getByDepartment(departmentId)
        );
    }
}
