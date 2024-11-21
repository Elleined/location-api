package com.elleined.philippinelocationapi.repository.region;

import com.elleined.philippinelocationapi.model.region.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}