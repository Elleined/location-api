package com.elleined.philippinelocationapi.service.city;

import com.elleined.philippinelocationapi.dto.CityDTO;
import com.elleined.philippinelocationapi.exception.AlreadyExistsException;
import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.mapper.CityMapper;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.repository.city.CityRepository;
import com.elleined.philippinelocationapi.service.province.ProvinceService;
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
    private final CityMapper cityMapper;

    private final ProvinceService provinceService;

    @Override
    public List<City> saveAll(List<CityDTO> cityDTOS) throws AlreadyExistsException {
        if (cityDTOS.stream().anyMatch(this::isAlreadyExists)) throw new AlreadyExistsException("Cannot save all cities! because one of the provided city id already exists!");

        List<City> cities = cityDTOS.stream()
                .map(cityDTO -> {
                    Province province = provinceService.getById(cityDTO.getProvinceId());
                    return cityMapper.toEntity(cityDTO, province);
                }).toList();

        cityRepository.saveAll(cities);
        log.debug("Saving all cities success...");
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
