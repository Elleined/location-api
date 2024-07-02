package com.elleined.philippinelocationapi.service.city;

import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.service.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService extends LocationService<City> {
    Page<City> getAllBy(Province province, Pageable pageable);
}
