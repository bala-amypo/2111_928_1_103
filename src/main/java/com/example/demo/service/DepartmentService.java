
package com.example.demo.service;

import com.example.demo.model.ShiftTemplate;
import java.util.List;

public interface ShiftTemplateService {
    ShiftTemplate create(ShiftTemplate template, Long departmentId);
    ShiftTemplate get(Long id);
    List<ShiftTemplate> getByDepartment(Long departmentId);
}
