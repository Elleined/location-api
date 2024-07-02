package com.elleined.philippinelocationapi.repository.baranggay;

import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaranggayRepository extends JpaRepository<Baranggay, Integer> {

    @Query("SELECT b FROM Baranggay b WHERE b.name LIKE CONCAT('%', :name, '%')")
    Page<Baranggay> findAllByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT b FROM Baranggay b WHERE b.city = :city")
    Page<Baranggay> findAll(@Param("city") City city, Pageable pageable);
}