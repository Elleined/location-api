package com.elleined.locationapi.service.newd;

import com.elleined.locationapi.dto.RegionDTO;
import com.elleined.locationapi.exception.AlreadyExistsException;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.mapper.RegionMapper;
import com.elleined.locationapi.model.City;
import com.elleined.locationapi.model.Province;
import com.elleined.locationapi.model.Region;
import com.elleined.locationapi.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Override
    public Region save(RegionDTO regionDTO) {
        if (this.isAlreadyExists(regionDTO)) throw new AlreadyExistsException("Region with id of " + regionDTO.getId() + " already exists!");
        Region region = regionMapper.toEntity(regionDTO);
        log.debug("Region with id of {} saved successfully", region.getId());
        return region;
    }

    @Override
    public List<Region> saveAll(List<RegionDTO> regionDTOS) {
        if (this.isAlreadyExists(regionDTOS)) throw new AlreadyExistsException("One of the provided id already exists!");
        List<Region> regions = regionDTOS.stream()
                .map(regionMapper::toEntity)
                .toList();
        regionRepository.saveAll(regions);
        log.debug("Saving all regions success");
        return regions;
    }

    @Override
    public Region getById(int id) throws ResourceNotFoundException {
        return regionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Region with id of " + id + " does not exists!"));
    }

    @Override
    public List<Region> getAll() {
        return regionRepository.findAll().stream()
                .sorted(Comparator.comparing(Region::getLocationName))
                .toList();
    }

    @Override
    public int getCityCount(Region region) {
        return (int) region.getProvinces().stream()
                .map(Province::getCities)
                .count();
    }

    @Override
    public int getBaranggayCount(Region region) {
        return (int) region.getProvinces().stream()
                .map(Province::getCities)
                .flatMap(Collection::stream)
                .map(City::getBaranggays)
                .count();
    }

    @Override
    public boolean isAlreadyExists(RegionDTO regionDTO) {
        return regionRepository.existsById(regionDTO.getId());
    }

    @Override
    public boolean isAlreadyExists(List<RegionDTO> regionDTOS) {
        return regionDTOS.stream().anyMatch(this::isAlreadyExists);
    }

    @Override
    public List<Region> searchByLocationName(String locationName) {
        return regionRepository.searchByLocationName(locationName).stream()
                .sorted(Comparator.comparing(Region::getLocationName))
                .toList();
    }
}
