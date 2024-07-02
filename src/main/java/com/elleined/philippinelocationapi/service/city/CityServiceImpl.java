package com.elleined.philippinelocationapi.service.city;

import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.repository.city.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public City getById(int id) throws ResourceNotFoundException {
        return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City with id of " + id + " does not exists!"));
    }

    @Override
    public Page<City> findAllByName(String name, Pageable pageable) {
        return cityRepository.findAllByName(name, pageable);
    }

    @Override
    public Page<City> getAllBy(Province province, Pageable pageable) {
        return cityRepository.findAll(province, pageable);

    }
}
