package com.example.travelAgency.dtos;

public class ResponseReservationDTO {
    private Long id;
    private String contactName;
    private String phoneNumber;
    private ResponseHolidayDTO holiday;

    public ResponseReservationDTO() {
    }

    public ResponseReservationDTO(Long id, String contactName, String phoneNumber, ResponseHolidayDTO holiday) {
        this.id = id;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.holiday = holiday;
    }

    public Long getId() {
        return id;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ResponseHolidayDTO getHoliday() {
        return holiday;
    }
}
