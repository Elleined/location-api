package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.mapper.ProvinceMapper;
import com.elleined.locationapi.model.location.Province;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {
    private final ProvinceService provinceService;
    private final ProvinceMapper provinceMapper;

    public ProvinceDTO save(String name, int regionId) throws IllegalArgumentException {
        if (regionId < 0) throw new IllegalArgumentException("Cannot save a province with region id of negative!");
        if (Validator.isNotValidBody(name)) throw new IllegalArgumentException("Cannot save a province with name of either empty, blank, or null!");
        Province province = provinceService.save(name, regionId);
        return provinceMapper.toDTO(province);
    }

}
