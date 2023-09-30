package com.elleined.locationapi.service.region;

import com.elleined.locationapi.dto.RegionDTO;
import com.elleined.locationapi.model.Region;
import com.elleined.locationapi.service.LocationService;

import java.util.List;

public interface RegionService extends LocationService<Region, RegionDTO> {
    List<Region> getAll();
}
