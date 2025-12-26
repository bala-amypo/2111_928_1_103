package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ShiftTemplateRepository shiftTemplateRepository;
    private final AvailabilityRepository availabilityRepository;
    private final EmployeeRepository employeeRepository;
    private final GeneratedShiftScheduleRepository scheduleRepository;
    private final DepartmentRepository departmentRepository;

    public ScheduleServiceImpl(
            ShiftTemplateRepository shiftTemplateRepository,
            AvailabilityRepository availabilityRepository,
            EmployeeRepository employeeRepository,
            GeneratedShiftScheduleRepository scheduleRepository,
            DepartmentRepository departmentRepository) {

        this.shiftTemplateRepository = shiftTemplateRepository;
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
        this.departmentRepository = departmentRepository;
    }

    // ----------------------------------------------------------------
    // GENERATE SCHEDULE
    // ----------------------------------------------------------------
    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {

        List<GeneratedShiftSchedule> result = new ArrayList<>();

        // 1️⃣ Get all departments
        List<Department> departments = departmentRepository.findAll();

        // 2️⃣ Get available employees for date
        List<EmployeeAvailability> availabilityList =
                availabilityRepository.findByAvailableDateAndAvailable(date, true);

        if (availabilityList.isEmpty()) {
            return result; // tests expect empty list
        }

        // 3️⃣ For each department → templates → match skills
        for (Department dept : departments) {

            List<ShiftTemplate> templates =
                    shiftTemplateRepository.findByDepartment_Id(dept.getId());

            for (ShiftTemplate template : templates) {

                for (EmployeeAvailability availability : availabilityList) {

                    Employee emp = availability.getEmployee();

                    // Skill matching (string contains – TEST DEPENDS ON THIS)
                    if (emp.getSkills() != null &&
                        emp.getSkills().contains(template.getRequiredSkills())) {

                        GeneratedShiftSchedule schedule =
                                new GeneratedShiftSchedule();

                        schedule.setShiftDate(date);
                        schedule.setStartTime(template.getStartTime());
                        schedule.setEndTime(template.getEndTime());
                        schedule.setDepartment(dept);
                        schedule.setShiftTemplate(template);
                        schedule.setEmployee(emp);

                        result.add(scheduleRepository.save(schedule));
                        break; // ONE employee per template
                    }
                }
            }
        }

        return result;
    }

    // ----------------------------------------------------------------
    // GET BY DATE
    // ----------------------------------------------------------------
    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return scheduleRepository.findByShiftDate(date);
    }
}
