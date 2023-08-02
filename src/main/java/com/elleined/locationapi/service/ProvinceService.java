package com.elleined.locationapi.service;

import com.elleined.locationapi.model.location.Province;
import com.elleined.locationapi.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    Province save(String name, int regionId) {
        Province province = Province.provinceBuilder()
                .locationName(name)
                .regionId(regionId)
                .cities(new HashSet<>())
                .build();


        provinceRepository.save(province);
        log.debug("Province with id of {} and name of {} saved successfully", province.getId(), name);
        return province;
    }
}
