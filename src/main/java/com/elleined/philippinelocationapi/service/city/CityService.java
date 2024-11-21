package com.elleined.philippinelocationapi.service.city;

import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;

import java.util.List;

public interface CityService {
    List<City> getAllBy(Region region, Province province);
    City getById(Region region, Province province, int id);
}
