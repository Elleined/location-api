package com.elleined.locationapi.service;

import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import com.elleined.locationapi.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    void save(Province province) {
        provinceRepository.save(province);
        log.debug("Province with id of {} and with name of {} saved successfully", province.getId(), province.getLocationName());
    }

    void saveAll(Set<Province> provinces) {
        provinceRepository.saveAll(provinces);
    }

    public Province getById(int id) throws ResourceNotFoundException {
        return provinceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Province with id of " + id + " does not exists"));
    }

    public int getBaranggayCount(Province province) {
        return (int) province.getCities().stream()
                .map(City::getBaranggays)
                .count();

    }
}
