package com.example.travelAgency.data;

import com.example.travelAgency.dtos.ResponseLocationDTO;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "location")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String street;
    private String number;
    private String city;
    private String country;
    private String imageUrl;

    public Location() {
    }

    public Location( String street, String number, String city, String country, String imageUrl) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ResponseLocationDTO toDTO(){
        return new ResponseLocationDTO(id, street, number, city, country, imageUrl);
    }

}