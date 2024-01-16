package com.example.travelAgency.dtos;

public class UpdateReservationDTO {
    private Long id;
    private String contactName;
    private String phoneNumber;
    private Long holiday;

    public Long getId() {
        return id;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getHoliday() {
        return holiday;
    }
}
