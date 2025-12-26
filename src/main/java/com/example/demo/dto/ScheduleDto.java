package com.example.demo.dto;

import java.time.LocalDate;

public class ScheduleDto {

    private LocalDate shiftDate;

    public LocalDate getShiftDate() {
        return shiftDate;
    }
 
    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }
}
