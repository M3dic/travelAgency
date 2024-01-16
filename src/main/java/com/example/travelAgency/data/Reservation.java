package com.example.travelAgency.data;

import com.example.travelAgency.dtos.ResponseReservationDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String contactName;
    @Size(max = 10)
    private String phoneNumber;

    @ManyToOne
    private Holiday holiday;

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }

    public ResponseReservationDTO toDto(){
        return new ResponseReservationDTO(id, contactName, phoneNumber, holiday.toDto());
    }
}

