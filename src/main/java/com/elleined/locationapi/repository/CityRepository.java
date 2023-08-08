package com.elleined.locationapi.repository;

import com.elleined.locationapi.model.location.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("SELECT c FROM City c WHERE c.locationName LIKE CONCAT('%', :locationName, '%')")
    List<City> searchByLocationName(@Param("locationName") String locationName);
}