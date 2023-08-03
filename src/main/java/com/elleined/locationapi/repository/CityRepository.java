package com.elleined.locationapi.repository;

import com.elleined.locationapi.model.location.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByZipCode(int zipCode);

    @Query("SELECT c.zipCode FROM City c")
    List<Integer> fetchAllZipCode();
}