package com.elleined.philippinelocationapi.repository.region;

import com.elleined.philippinelocationapi.model.region.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    @Query("SELECT r FROM Region r WHERE r.name LIKE CONCAT('%', :name, '%') ORDER BY r.id")
    Page<Region> findAllByName(@Param("name") String name, Pageable pageable);
}