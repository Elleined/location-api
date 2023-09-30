package com.elleined.locationapi.service.province;

import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.model.Province;
import com.elleined.locationapi.model.Region;
import com.elleined.locationapi.service.LocationService;

import java.util.List;

public interface ProvinceService extends LocationService<Province, ProvinceDTO> {
    List<Province> getAllBy(Region region);
}
