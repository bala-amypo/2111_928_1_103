package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.GeneratedShiftScheduleRepository;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final AvailabilityRepository availabilityRepository;
    private final ShiftTemplateRepository shiftTemplateRepository;
    private final GeneratedShiftScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(
            AvailabilityRepository availabilityRepository,
            ShiftTemplateRepository shiftTemplateRepository,
            GeneratedShiftScheduleRepository scheduleRepository) {

        this.availabilityRepository = availabilityRepository;
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    @Transactional
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {

        List<EmployeeAvailability> availableEmployees =
                availabilityRepository.findByAvailableDateAndAvailable(date, true);

        System.out.println("Available employees: " + availableEmployees.size());

        List<ShiftTemplate> shiftTemplates = shiftTemplateRepository.findAll();
        System.out.println("Shift templates: " + shiftTemplates.size());

        List<GeneratedShiftSchedule> schedules = new ArrayList<>();

        for (EmployeeAvailability availability : availableEmployees) {
            for (ShiftTemplate template : shiftTemplates) {

                GeneratedShiftSchedule schedule = new GeneratedShiftSchedule();
                schedule.setShiftDate(date);
                schedule.setStartTime(template.getStartTime());
                schedule.setEndTime(template.getEndTime());
                schedule.setEmployeeId(availability.getEmployeeId());
                schedule.setDepartmentId(template.getDepartmentId());
                schedule.setShiftTemplateId(template.getId());

                schedules.add(schedule);
            }
        }

        // 🔥 THIS LINE ACTUALLY INSERTS INTO DATABASE
        return scheduleRepository.saveAll(schedules);
    }

    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return scheduleRepository.findByShiftDate(date);
    }
}
