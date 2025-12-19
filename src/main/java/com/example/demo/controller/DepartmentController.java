package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Validated
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public Department create(@Valid @RequestBody Department department) {
        return departmentService.create(department);
    }

    @GetMapping("/{id}")
    public Department get(@PathVariable @NotNull Long id) {
        return departmentService.get(id);
    }

    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull Long id) {
        departmentService.delete(id);
    }
}
