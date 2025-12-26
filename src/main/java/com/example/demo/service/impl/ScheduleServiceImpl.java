package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ShiftTemplateRepository templateRepository;
    private final EmployeeAvailabilityRepository availabilityRepository;
    private final GeneratedShiftScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ShiftTemplateRepository templateRepository,
                               EmployeeAvailabilityRepository availabilityRepository,
                               GeneratedShiftScheduleRepository scheduleRepository) {
        this.templateRepository = templateRepository;
        this.availabilityRepository = availabilityRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {

        List<EmployeeAvailability> availableEmployees =
                availabilityRepository.findByAvailableDateAndAvailableTrue(date);

        List<ShiftTemplate> templates = templateRepository.findAll();
        List<GeneratedShiftSchedule> schedules = new ArrayList<>();

        for (ShiftTemplate template : templates) {
            for (EmployeeAvailability availability : availableEmployees) {

                Employee emp = availability.getEmployee();

                if (emp.getSkills().contains(template.getRequiredSkills())) {
                    GeneratedShiftSchedule schedule = new GeneratedShiftSchedule();
                    schedule.setShiftDate(date);
                    schedule.setStartTime(template.getStartTime());
                    schedule.setEndTime(template.getEndTime());
                    schedule.setDepartment(template.getDepartment());
                    schedule.setShiftTemplate(template);
                    schedule.setEmployee(emp);

                    schedules.add(scheduleRepository.save(schedule));
                    break; // first qualified employee
                }
            }
        }
        return schedules;
    }

    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return scheduleRepository.findByShiftDate(date);
    }
}
