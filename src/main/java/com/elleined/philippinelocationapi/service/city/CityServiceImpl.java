package com.elleined.philippinelocationapi.service.city;

import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.repository.city.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> saveAll(List<City> cities) {
        return cityRepository.saveAll(cities);
    }

    @Override
    public boolean existsById(int id) {
        return cityRepository.existsById(id);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City getById(int id) throws ResourceNotFoundException {
        return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City with id of " + id + " does not exists!"));
    }

    @Override
    public List<City> getAllById(List<Integer> ids) {
        return cityRepository.findAllById(ids);
    }

    @Override
    public List<City> searchByName(String name) {
        return cityRepository.searchByLocationName(name).stream()
                .sorted(Comparator.comparing(Location::getName))
                .toList();
    }

    @Override
    public List<City> getAllBy(Province province) {
        return province.getCities().stream()
                .sorted(Comparator.comparing(Location::getName))
                .toList();
    }
}
