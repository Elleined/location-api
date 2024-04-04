package com.elleined.philippinelocationapi.repository.province;

import com.elleined.philippinelocationapi.model.province.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("SELECT p FROM Province p WHERE p.name LIKE CONCAT('%', :name, '%') ORDER BY p.id")
    List<Province> searchByLocationName(@Param("name") String locationName);
}