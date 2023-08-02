package com.elleined.locationapi.repository;

import com.elleined.locationapi.model.location.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByZipCode(int zipCode);
}