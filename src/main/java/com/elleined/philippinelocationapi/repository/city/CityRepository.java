package com.elleined.philippinelocationapi.repository.city;

import com.elleined.philippinelocationapi.model.city.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("SELECT c FROM City c WHERE c.name LIKE CONCAT('%', :name, '%')")
    Page<City> findAllByName(@Param("name") String name, Pageable pageable);
}