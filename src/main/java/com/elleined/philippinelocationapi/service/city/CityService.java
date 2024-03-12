package com.elleined.philippinelocationapi.service.city;

import com.elleined.philippinelocationapi.dto.CityDTO;
import com.elleined.philippinelocationapi.model.City;
import com.elleined.philippinelocationapi.model.Province;
import com.elleined.philippinelocationapi.service.LocationService;

import java.util.List;

public interface CityService extends LocationService<City, CityDTO> {
    List<City> getAllBy(Province province);
}