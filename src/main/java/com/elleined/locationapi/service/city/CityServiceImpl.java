package com.elleined.locationapi.service.city;

import com.elleined.locationapi.dto.CityDTO;
import com.elleined.locationapi.exception.AlreadyExistsException;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.mapper.CityMapper;
import com.elleined.locationapi.model.City;
import com.elleined.locationapi.model.Province;
import com.elleined.locationapi.repository.CityRepository;
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
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public City save(CityDTO cityDTO) {
        if (isAlreadyExists(cityDTO)) throw new AlreadyExistsException("City with id of " + cityDTO.getId() + " already exists!");
        City city = cityMapper.toEntity(cityDTO);
        cityRepository.save(city);
        log.debug("City with id of {} and with name of {} saved successfully!", city.getId(), city.getLocationName());
        return city;
    }

    @Override
    public List<City> saveAll(List<CityDTO> cityDTOS) {
        if (isAlreadyExists(cityDTOS)) throw new AlreadyExistsException("One of the provided id already exists!");
        List<City> cities = cityDTOS.stream()
                .map(cityMapper::toEntity)
                .toList();
        cityRepository.saveAll(cities);
        log.debug("Saving all cities success!");
        return cities;
    }

    @Override
    public City getById(int id) throws ResourceNotFoundException {
        return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City with id of " + id + " does not exists!"));
    }

    @Override
    public boolean isAlreadyExists(CityDTO cityDTO) {
        return cityRepository.existsById(cityDTO.getId());
    }

    @Override
    public boolean isAlreadyExists(List<CityDTO> cityDTOS) {
        return cityDTOS.stream().anyMatch(this::isAlreadyExists);
    }

    @Override
    public List<City> searchByLocationName(String locationName) {
        return cityRepository.searchByLocationName(locationName).stream()
                .sorted(Comparator.comparing(City::getLocationName))
                .toList();
    }

    @Override
    public List<City> getAllBy(Province province) {
        return province.getCities().stream()
                .sorted(Comparator.comparing(City::getLocationName))
                .toList();
    }
}
