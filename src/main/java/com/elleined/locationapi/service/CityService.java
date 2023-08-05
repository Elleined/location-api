package com.elleined.locationapi.service;


import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CityService {
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

    City getByZipCode(int zipCode) throws ResourceNotFoundException {
        return cityRepository.findByZipCode(zipCode).orElseThrow(() -> new ResourceNotFoundException("City with zipCode of " + zipCode + " does not exists!"));
    }

    public int getBaranggayCount(City city) {
        return city.getBaranggays().size();
    }

    boolean isZipCodeAlreadyExists(int zipCode) {
        return cityRepository.fetchAllZipCode().stream().anyMatch(code -> zipCode == code);
    }
}
