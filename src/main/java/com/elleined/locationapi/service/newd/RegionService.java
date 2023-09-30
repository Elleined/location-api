package com.elleined.locationapi.service.newd;

import com.elleined.locationapi.dto.RegionDTO;
import com.elleined.locationapi.model.Region;

import java.util.List;

public interface RegionService extends LocationService<Region, RegionDTO> {
    List<Region> getAll();
    int getCityCount(Region region);
    int getBaranggayCount(Region region);
}
