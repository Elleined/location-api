package com.elleined.locationapi.service;


import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
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

    Set<City> getAllByProvince(Province province) {
       return province.getCities();
    }

    void delete(int id) {
        cityRepository.deleteById(id);
        log.debug("City with id of {} deleted successfully!", id);
    }

    void update(City city, Province province, String name, int zipCode) {
        city.setProvince(province);
        city.setLocationName(name);
        city.setZipCode(zipCode);

        cityRepository.save(city);
        log.debug("City with id of {} updated with new province of {} and new name of {} and new zipCode of {}", city.getId(), province.getLocationName(), name, zipCode);
    }

    public int getBaranggayCount(City city) {
        return city.getBaranggays().size();
    }
}
