package com.example.demo.controller;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/generate/{date}")
    public List<GeneratedShiftSchedule> generate(@PathVariable String date) {
        return scheduleService.generateForDate(LocalDate.parse(date));
    }

    @GetMapping("/{date}")
    public List<GeneratedShiftSchedule> getByDate(@PathVariable String date) {
        return scheduleService.getByDate(LocalDate.parse(date));
    }
}
