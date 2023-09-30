package com.elleined.locationapi.service.city;

import com.elleined.locationapi.dto.CityDTO;
import com.elleined.locationapi.model.City;
import com.elleined.locationapi.model.Province;
import com.elleined.locationapi.service.LocationService;

import java.util.List;

public interface CityService extends LocationService<City, CityDTO> {
    List<City> getAllBy(Province province);
}
