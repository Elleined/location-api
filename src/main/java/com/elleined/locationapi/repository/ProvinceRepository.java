package com.elleined.locationapi.repository;

import com.elleined.locationapi.model.location.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("SELECT p FROM Province p WHERE p.locationName LIKE CONCAT('%', :locationName, '%') ORDER BY p.id")
    List<Province> searchByLocationName(@Param("locationName") String locationName);
}