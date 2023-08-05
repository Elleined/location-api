package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.RegionDTO;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import com.elleined.locationapi.model.location.Region;
import com.elleined.locationapi.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RegionService implements ExistenceChecker<RegionDTO> {

    private final RegionRepository regionRepository;

    void save(Region region) {
        regionRepository.save(region);
        log.debug("Region with id of {} saved successfully", region.getId());
    }

    void saveAll(Set<Region> regions) {
        regionRepository.saveAll(regions);
    }

    public Region getById(int id) throws ResourceNotFoundException {
        return regionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Region with id of " + id + " does not exists!"));
    }

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    public int getCityCount(Region region) {
        return (int) region.getProvinces().stream()
                .map(Province::getCities)
                .count();
    }

    public int getBaranggayCount(Region region) {
        return (int) region.getProvinces().stream()
                .map(Province::getCities)
                .flatMap(cities -> cities.stream()
                        .map(City::getBaranggays))
                .count();
    }

    @Override
    public boolean isAlreadyExists(RegionDTO regionDTO) {
        return regionRepository.existsById(regionDTO.getId());
    }

    @Override
    public boolean isAlreadyExists(Collection<RegionDTO> regionDTOS) {
        return regionDTOS.stream().anyMatch(this::isAlreadyExists);
    }
}
