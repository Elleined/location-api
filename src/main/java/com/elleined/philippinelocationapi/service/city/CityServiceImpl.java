package com.elleined.philippinelocationapi.service.city;

import com.elleined.philippinelocationapi.exception.resource.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.city.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public List<City> getAllBy(Region region, Province province) {
        return cityRepository.findAll(region, province);
    }

    @Override
    public City getById(Region region, Province province, int id) {
        if (!region.has(id))
            throw new ResourceNotFoundException("Region with id of " + region.getId() + " doesn't have province with id of " + province.getId());

        if (!province.has(id))
            throw new ResourceNotFoundException("Province with id of " + province.getId() + " doesn't have city with id of " + id);

        return cityRepository.findById(region, province, id).orElseThrow(() -> new ResourceNotFoundException("City with id of " + id + " does not exists!"));
    }

    @Override
    public City getById(int id) {
        return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City with id of " + id + " does not exists!"));
    }
}
