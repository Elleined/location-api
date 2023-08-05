package com.elleined.locationapi.service;


import com.elleined.locationapi.dto.CityDTO;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CityService implements ExistenceChecker<CityDTO> {
    private final CityRepository cityRepository;

    void save(City city) {
        cityRepository.save(city);
        log.debug("City with id of {} and with name of {} saved successfully!", city.getId(), city.getLocationName());
    }

    void saveAll(Set<City> cities) {
        cityRepository.saveAll(cities);
    }

    public City getById(int id) throws ResourceNotFoundException {
        return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City with id of " + id + " does not exists!"));
    }

    @Override
    public boolean isAlreadyExists(CityDTO cityDTO) {
        return cityRepository.existsById(cityDTO.getId());
    }

    @Override
    public boolean isAlreadyExists(Collection<CityDTO> cityDTOS) {
        return cityDTOS.stream().anyMatch(this::isAlreadyExists);
    }
}
