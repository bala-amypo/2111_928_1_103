package com.example.demo.service;

import com.example.demo.entity.ShiftTemplate;

import java.util.List;

public interface ShiftTemplateService {

    ShiftTemplate create(ShiftTemplate template, Long departmentId);

    List<ShiftTemplate> getByDepartment(Long departmentId);

    ShiftTemplate getById(Long id);
}
