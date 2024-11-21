package com.elleined.philippinelocationapi.service.region;

import com.elleined.philippinelocationapi.model.region.Region;

import java.util.List;

public interface RegionService {
    List<Region> getAll();
    Region getById(int id);
}
