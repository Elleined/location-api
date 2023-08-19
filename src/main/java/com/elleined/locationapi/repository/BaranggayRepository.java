package com.elleined.locationapi.repository;

import com.elleined.locationapi.model.Baranggay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaranggayRepository extends JpaRepository<Baranggay, Integer> {

    @Query("SELECT b FROM Baranggay b WHERE b.locationName LIKE CONCAT('%', :locationName, '%')")
    List<Baranggay> searchByLocationName(@Param("locationName") String locationName);
}