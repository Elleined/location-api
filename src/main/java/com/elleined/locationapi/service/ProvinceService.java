package com.elleined.locationapi.service;

import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import com.elleined.locationapi.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

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

    Province getById(int id) {
        return provinceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Province with id of " + id + " does not exists"));
    }

    List<Province> getAll() {
        return provinceRepository.findAll();
    }

    Page<Province> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);
        return provinceRepository.findAll(pageable);
    }

    Page<Province> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, sortDirection, sortProperty);
        return provinceRepository.findAll(pageable);
    }

    void delete(Province province) {
        provinceRepository.delete(province);

    }

    void update(Province province, String newName, int regionId) {
        province.setLocationName(newName);
        province.setRegionId(regionId);

        provinceRepository.save(province);
        log.debug("Province with id of {} updated with new name of {}", province.getId(), newName);
    }

    public int getCityCount(Province province) {
        return province.getCities().size();
    }

    public int getBaranggayCount(Province province) {
        return (int) province.getCities().stream()
                .map(City::getBaranggays)
                .count();

    }
}
