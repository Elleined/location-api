package com.elleined.locationapi.repository;

import com.elleined.locationapi.model.location.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}