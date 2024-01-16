package com.example.travelAgency.dtos;

public class ResponseLocationDTO {
    private Long id;
    private String street;
    private String number;
    private String city;
    private String country;
    private String imageUrl;

    public ResponseLocationDTO() {
    }

    public ResponseLocationDTO(Long id, String street, String number, String city, String country, String imageUrl) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
