package com.elleined.philippinelocationapi.service.region;

import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RegionService extends LocationService<Region> {
    Page<Region> getAll(Pageable pageable);
}
