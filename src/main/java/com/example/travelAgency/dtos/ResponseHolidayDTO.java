package com.example.travelAgency.dtos;

import java.time.LocalDate;

public class ResponseHolidayDTO {
    private Long id;
    private ResponseLocationDTO location;
    private String title;
    private LocalDate startDate;
    private int duration;
    private double price;
    private int freeSlots;

    public ResponseHolidayDTO() {
    }

    public ResponseHolidayDTO(Long id, ResponseLocationDTO location, String title, LocalDate startDate, int duration, double price, int freeSlots) {
        this.id = id;
        this.location = location;
        this.title = title;
        this.startDate = startDate;
        this.duration = duration;
        this.price = price;
        this.freeSlots = freeSlots;
    }

    public Long getId() {
        return id;
    }

    public ResponseLocationDTO getLocation() {
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
