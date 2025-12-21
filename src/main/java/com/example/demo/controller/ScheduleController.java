package com.example.demo.controller;

import com.example.demo.entity.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@Tag(name = "Shift Schedules Endpoints")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/generate/{date}")
    public List<GeneratedShiftSchedule> generate(
            @NotNull @PathVariable LocalDate date) {
        return scheduleService.generateForDate(date);
    }

    @GetMapping("/date/{date}")
    public List<GeneratedShiftSchedule> getByDate(
            @NotNull @PathVariable LocalDate date) {
        return scheduleService.getByDate(date);
    }
}
