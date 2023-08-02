package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.CityDTO;
import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.mapper.CityMapper;
import com.elleined.locationapi.mapper.ProvinceMapper;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import com.elleined.locationapi.utility.StringValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {
    private final ProvinceService provinceService;
    private final CityService cityService;

    private final ProvinceMapper provinceMapper;
    private final CityMapper cityMapper;

    public ProvinceDTO saveProvince(String name, int regionId) {
        Province province = provinceService.save(name, regionId);
        return provinceMapper.toDTO(province);
    }

    public CityDTO saveCity(int provinceId, String name, int zipCode) {
        Province province = provinceService.getById(provinceId);
        City city = cityService.save(province, name, zipCode);
        return cityMapper.toDTO(city);
    }
}
