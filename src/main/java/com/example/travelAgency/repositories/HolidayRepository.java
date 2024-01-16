package com.example.travelAgency.repositories;

import com.example.travelAgency.data.Holiday;
import com.example.travelAgency.data.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Holiday save(Holiday holiday);

    Optional<Holiday> findById(Long id);

    List<Holiday> findAll();
    List<Holiday> findByLocation(Location location);
    List<Holiday> findByDuration(int duration);
    List<Holiday> findByStartDate(LocalDate startDate);

    void delete(Holiday holiday);
    @Query("SELECT hol FROM holiday hol" +
            " WHERE hol.location.id = :location_Id AND hol.duration = :duration")
    List<Holiday> findByLocationAndDuration(@Param("location_Id")Long location, @Param("duration")int duration);
    @Query("SELECT hol FROM holiday hol" +
            " WHERE hol.duration = :duration AND hol.startDate = :startDate")
    List<Holiday> findByDurationAndStartDate(int duration, LocalDate startDate);

    @Query("SELECT hol FROM holiday hol" +
            " WHERE hol.location.id = :location_Id AND hol.startDate = :startDate")
    List<Holiday> findByLocationAndStartDate(@Param("location_Id")Long location,@Param("startDate") LocalDate startDate);
    @Query("SELECT hol FROM holiday hol" +
            " WHERE hol.location.id = :location_Id AND hol.duration = :duration AND hol.startDate = :startDate")
    List<Holiday> findByLocationAndDurationAndStartDate(@Param("location_Id")Long location,@Param("duration")int duration, @Param("startDate")LocalDate startDate);


    void deleteById(Long id);
    boolean existsById(Long id);
}