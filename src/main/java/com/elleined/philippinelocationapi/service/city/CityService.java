package com.elleined.philippinelocationapi.service.city;

import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService extends LocationService<City> {
    Page<City> getAllBy(Region region, Province province, Pageable pageable);
    List<City> getAllBy(Region region, Province province);

    Page<City> findAllByName(Region region, Province province, String name, Pageable pageable);
}
