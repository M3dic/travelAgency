package com.example.travelAgency.dtos;

public class CreateReservationDTO {
    private String contactName;
    private String phoneNumber;
    private Long holiday;

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
