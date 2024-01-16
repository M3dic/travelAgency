package com.example.travelAgency.controllers;

import com.example.travelAgency.data.Location;
import com.example.travelAgency.dtos.CreateLocationDTO;
import com.example.travelAgency.dtos.ResponseLocationDTO;
import com.example.travelAgency.dtos.UpdateLocationDTO;
import com.example.travelAgency.repositories.LocationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    public LocationController(){
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseLocationDTO> createLocation(@Valid @RequestBody CreateLocationDTO locationDTO){
        if(locationDTO.getCity() == null && locationDTO.getCountry() == null && locationDTO.getImageUrl() == null
                && locationDTO.getNumber() == null && locationDTO.getStreet() == null){
            return ResponseEntity.badRequest().build();
        }
        Location locationToCreate = new Location();
        locationToCreate.setNumber(locationDTO.getNumber());
        locationToCreate.setStreet(locationDTO.getStreet());
        locationToCreate.setCountry(locationDTO.getCountry());
        locationToCreate.setCity(locationDTO.getCity());
        locationToCreate.setImageUrl(locationDTO.getImageUrl());
        Location location =  locationRepository.save(locationToCreate);
        return ResponseEntity.ok(location.toDTO());
    }

    @DeleteMapping("/{locationId}")
    public boolean deleteLocation(@Valid @PathVariable Long locationId){
        if(!locationRepository.existsById(locationId)){
            return true;
        }
        locationRepository.deleteById(locationId);
        return !locationRepository.existsById(locationId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseLocationDTO>> getAllLocation(){
        List<Location> locations = locationRepository.findAll();
        List<ResponseLocationDTO> response = new ArrayList<>();
        for(Location location: locations){
            response.add(location.toDTO());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{locationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseLocationDTO> getLocationById(@Valid @PathVariable Long locationId){
        if(!locationRepository.findById(locationId).isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(locationRepository.findById(locationId).get().toDTO());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseLocationDTO> updateLocation(@Valid @RequestBody UpdateLocationDTO locationDTO){
        if(locationDTO.getId() == null || locationDTO.getId() == 0){
            return ResponseEntity.notFound().build();
        }
        if(locationDTO.getCity() == null && locationDTO.getCountry() == null && locationDTO.getImageUrl() == null
                && locationDTO.getNumber() == null && locationDTO.getStreet() == null ){
            return ResponseEntity.badRequest().build();
        }
        Location location = new Location();
        location.setId(locationDTO.getId());
        location.setNumber(locationDTO.getNumber());
        location.setStreet(locationDTO.getStreet());
        location.setCountry(locationDTO.getCountry());
        location.setCity(locationDTO.getCity());
        location.setImageUrl(locationDTO.getImageUrl());
        Optional<Location> locationToUpdate = locationRepository.findById(location.getId());
        if(!locationToUpdate.isPresent()){
            return ResponseEntity.notFound().build();
        }

        if(location.getCity() != null){
            locationToUpdate.get().setCity(location.getCity());
        }
        if(location.getCountry() != null){
            locationToUpdate.get().setCountry(location.getCountry());
        }
        if(location.getImageUrl() != null){
            locationToUpdate.get().setImageUrl(location.getImageUrl());
        }
        if(location.getStreet() != null){
            locationToUpdate.get().setStreet(location.getStreet());
        }
        if(location.getNumber() != null){
            locationToUpdate.get().setNumber(location.getNumber());
        }

        Location updatedLocation = locationRepository.save(locationToUpdate.get());
        return ResponseEntity.ok(updatedLocation.toDTO());

    }
}
