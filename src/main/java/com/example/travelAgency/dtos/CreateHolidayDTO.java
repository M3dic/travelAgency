package com.example.travelAgency.dtos;

import java.time.LocalDate;

public class CreateHolidayDTO {
    private String title;
    private LocalDate startDate;
    private int duration;
    private double price;
    private int freeSlots;
    private Long location;

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public Long getLocation() {
        return location;
    }
}
