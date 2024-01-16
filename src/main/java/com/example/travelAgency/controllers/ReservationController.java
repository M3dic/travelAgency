package com.example.travelAgency.controllers;

import com.example.travelAgency.data.Holiday;
import com.example.travelAgency.data.Reservation;
import com.example.travelAgency.dtos.CreateReservationDTO;
import com.example.travelAgency.dtos.ResponseReservationDTO;
import com.example.travelAgency.dtos.UpdateReservationDTO;
import com.example.travelAgency.repositories.HolidayRepository;
import com.example.travelAgency.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    HolidayRepository holidayService;
    @PostMapping
    public ResponseEntity<ResponseReservationDTO> createReservation(@RequestBody CreateReservationDTO reservationDTO){
        if(reservationDTO.getHoliday() == 0){
            return ResponseEntity.notFound().build();
        }
        Optional<Holiday> holiday = holidayService.findById(reservationDTO.getHoliday());
        if(!holiday.isPresent()){
            return ResponseEntity.notFound().build();
        }

        if(reservationDTO.getHoliday() == 0 && reservationDTO.getContactName() == null && reservationDTO.getPhoneNumber() == null ){
            return ResponseEntity.badRequest().build();
        }
        Reservation reservation = new Reservation();
        reservation.setPhoneNumber(reservationDTO.getPhoneNumber());
        reservation.setContactName(reservationDTO.getContactName());
        reservation.setHoliday(holiday.get());
        Reservation reservationCreated = reservationRepository.save(reservation);
        return ResponseEntity.ok(reservationCreated.toDto());
    }

    @DeleteMapping("/{reservationId}")
    public boolean deleteReservation(@PathVariable Long reservationId){
        if(!reservationRepository.existsById(reservationId)){
            return true;
        }
        reservationRepository.deleteById(reservationId);
        return !reservationRepository.existsById(reservationId);
    }

    @GetMapping
    public ResponseEntity<List<ResponseReservationDTO>> getAllReservationS(){
        List<Reservation> reservations = reservationRepository.findAll();
        List<ResponseReservationDTO> response = new ArrayList<>();
        for(Reservation reservation:reservations){
            response.add(reservation.toDto());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ResponseReservationDTO> getReservationById(@PathVariable Long reservationId){
        if(!reservationRepository.existsById(reservationId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservationRepository.findById(reservationId).get().toDto());
    }

    @PutMapping
    public ResponseEntity<ResponseReservationDTO> updateReservation(@RequestBody UpdateReservationDTO reservationDTO){
        if(reservationDTO.getId() == 0){
            return ResponseEntity.notFound().build();
        }

        if(reservationDTO.getHoliday() == 0){
            return ResponseEntity.badRequest().build();
        }
        Optional<Holiday> holiday = holidayService.findById(reservationDTO.getHoliday());
        if(!holiday.isPresent()){
            return ResponseEntity.notFound().build();
        }


        if(reservationDTO.getHoliday() == 0 && reservationDTO.getContactName() == null && reservationDTO.getPhoneNumber() == null ){
            return ResponseEntity.badRequest().build();
        }
        Reservation reservation = new Reservation();
        reservation.setPhoneNumber(reservationDTO.getPhoneNumber());
        reservation.setContactName(reservationDTO.getContactName());
        reservation.setHoliday(holiday.get());

        Optional<Reservation> reservationToUpdate = reservationRepository.findById(reservationDTO.getId());
        if(!reservationToUpdate.isPresent()){
            return ResponseEntity.notFound().build();
        }

        if(reservation.getContactName() != null){
            reservationToUpdate.get().setContactName(reservation.getContactName());
        }
        if(reservation.getPhoneNumber() != null ){
            reservationToUpdate.get().setPhoneNumber(reservation.getPhoneNumber());
        }
        if(reservation.getHoliday() != null){
            reservationToUpdate.get().setHoliday(reservation.getHoliday());
        }
        Reservation updatedReservation = reservationRepository.save(reservationToUpdate.get());

        return ResponseEntity.ok(updatedReservation.toDto());
    }
}
