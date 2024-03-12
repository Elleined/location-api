package com.elleined.philippinelocationapi.service.province;

import com.elleined.philippinelocationapi.dto.ProvinceDTO;
import com.elleined.philippinelocationapi.model.Province;
import com.elleined.philippinelocationapi.model.Region;
import com.elleined.philippinelocationapi.service.LocationService;

import java.util.List;

public interface ProvinceService extends LocationService<Province, ProvinceDTO> {
    List<Province> getAllBy(Region region);
}