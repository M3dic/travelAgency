package com.example.travelAgency.controllers;

import com.example.travelAgency.data.Holiday;
import com.example.travelAgency.data.Location;
import com.example.travelAgency.dtos.CreateHolidayDTO;
import com.example.travelAgency.dtos.ResponseHolidayDTO;
import com.example.travelAgency.dtos.UpdateHolidayDTO;
import com.example.travelAgency.repositories.HolidayRepository;
import com.example.travelAgency.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/holidays")
public class HolidayController {

    @Autowired
    HolidayRepository holidayRepository;
    @Autowired
    private LocationRepository locationRepository;

    @PostMapping
    public ResponseEntity<ResponseHolidayDTO> createHoliday(@RequestBody CreateHolidayDTO holidayDTO){
        if(holidayDTO.getLocation() == 0){
            return ResponseEntity.notFound().build();
        }
        Location location = locationRepository.findById(holidayDTO.getLocation()).get();

        if(holidayDTO.getDuration() == 0 && holidayDTO.getLocation() == null && holidayDTO.getFreeSlots() == 0
                && holidayDTO.getPrice() == 0 && holidayDTO.getTitle() == null && holidayDTO.getStartDate() == null ){
           return ResponseEntity.badRequest().build();
        }
        Holiday holiday = new Holiday(holidayDTO.getTitle(), holidayDTO.getStartDate(), holidayDTO.getDuration(), holidayDTO.getPrice(), holidayDTO.getFreeSlots(), location);

        Holiday holidayCreated = holidayRepository.save(holiday);
        return ResponseEntity.ok(holidayCreated.toDto());
    }

    @DeleteMapping("/{holidayId}")
    public boolean deleteHoliday(@PathVariable Long holidayId){
        if(!holidayRepository.existsById(holidayId)){
            return true;
        }
        holidayRepository.deleteById(holidayId);
        return !holidayRepository.existsById(holidayId);
    }

    @GetMapping
    public ResponseEntity<List<ResponseHolidayDTO>> getHolidaysByFilter(@RequestParam(required = false) Long location,@RequestParam(required = false) LocalDate startDate ,@RequestParam(required = false) Integer duration ){
        List<Holiday> list = new ArrayList<>();
        List<ResponseHolidayDTO> holidaysDto = new ArrayList<>();
        if(location != null && startDate != null && duration != null){
            list = holidayRepository.findByLocationAndDurationAndStartDate(location, duration.intValue(), startDate);
        }else if(location != null && startDate != null){
            list = holidayRepository.findByLocationAndStartDate(location, startDate);
        }else if(location != null && duration != null){
            list = holidayRepository.findByLocationAndDuration(location, duration.intValue());
        }else if(startDate != null && duration != null){
            list = holidayRepository.findByDurationAndStartDate(duration.intValue(), startDate);
        }else if(location != null){
            if(locationRepository.findById(location).isPresent()){
                list = holidayRepository.findByLocation(locationRepository.findById(location).get());
            }else {
                return ResponseEntity.notFound().build();
            }

        }else if(duration != null){
            list = holidayRepository.findByDuration(duration);
        } else if (startDate != null) {
            list = holidayRepository.findByStartDate(startDate);
        }else{
            list = holidayRepository.findAll();
        }

        if(list.size() == 0){
           return ResponseEntity.notFound().build();
        }else{
            for(Holiday holiday:list){
            holidaysDto.add(holiday.toDto());
            }
            return ResponseEntity.ok(holidaysDto);
        }
    }

    @GetMapping("/{holidayId}")
    public ResponseEntity<ResponseHolidayDTO> getHolidayById(@PathVariable Long holidayId){
        if(!holidayRepository.existsById(holidayId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(holidayRepository.findById(holidayId).get().toDto());
    }

    @PutMapping
    public ResponseEntity<ResponseHolidayDTO> updateHoliday(@RequestBody UpdateHolidayDTO holidayDTO){
        if(holidayDTO.getId() == null || holidayDTO.getId() == 0){
            return ResponseEntity.notFound().build();
        }
        if(holidayDTO.getDuration() == 0 && holidayDTO.getLocation() == null && holidayDTO.getFreeSlots() == 0
                && holidayDTO.getPrice() == 0 && holidayDTO.getTitle() == null && holidayDTO.getStartDate() == null ){
            return ResponseEntity.badRequest().build();
        }
        Optional<Location> location = locationRepository.findById(holidayDTO.getLocation());
        if(!location.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Holiday holiday = new Holiday(holidayDTO.getTitle(), holidayDTO.getStartDate(), holidayDTO.getDuration(), holidayDTO.getPrice(), holidayDTO.getFreeSlots(), location.get());


        Optional<Holiday> holidayToUpdate = holidayRepository.findById(holidayDTO.getId());
        if(!holidayToUpdate.isPresent()){
            return ResponseEntity.notFound().build();
        }

        if(holiday.getDuration() != 0){
            holidayToUpdate.get().setDuration(holiday.getDuration());
        }
        if(holiday.getFreeSlots() > 0 ){
            holidayToUpdate.get().setFreeSlots(holiday.getFreeSlots());
        }
        if(holiday.getLocation() != null){
            holidayToUpdate.get().setLocation(holiday.getLocation());
        }
        if(holiday.getPrice() > 0){
            holidayToUpdate.get().setPrice(holiday.getPrice());
        }
        if(holiday.getTitle() != null){
            holidayToUpdate.get().setTitle(holiday.getTitle());
        }

        if(holiday.getStartDate() != null){
            holidayToUpdate.get().setStartDate(holiday.getStartDate());
        }
        Holiday updatedHoliday = holidayRepository.save(holidayToUpdate.get());
        return ResponseEntity.ok(updatedHoliday.toDto());
    }
}
