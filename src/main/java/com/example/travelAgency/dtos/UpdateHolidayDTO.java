package com.example.travelAgency.dtos;

import java.time.LocalDate;

public class UpdateHolidayDTO {
    private Long id;
    private Long location;
    private String title;
    private LocalDate startDate;
    private int duration;
    private double price;
    private int freeSlots;

    public Long getId() {
        return id;
    }

    public Long getLocation() {
        return location;
    }

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
}