package com.elleined.locationapi.service.region;

import com.elleined.locationapi.dto.RegionDTO;
import com.elleined.locationapi.exception.AlreadyExistsException;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.mapper.RegionMapper;
import com.elleined.locationapi.model.Region;
import com.elleined.locationapi.repository.RegionRepository;
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
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Override
    public Region save(RegionDTO regionDTO) {
        if (this.isAlreadyExists(regionDTO)) throw new AlreadyExistsException("Region with id of " + regionDTO.getId() + " already exists!");
        Region region = regionMapper.toEntity(regionDTO);
        regionRepository.save(region);
        log.debug("Region with id of {} saved successfully", region.getId());
        return region;
    }

    @Override
    public List<Region> saveAll(List<RegionDTO> regionDTOS) {
        return regionDTOS.stream()
                .map(this::save)
                .toList();
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
    public boolean isAlreadyExists(RegionDTO regionDTO) {
        return regionRepository.existsById(regionDTO.getId());
    }

    @Override
    public List<Region> searchByLocationName(String locationName) {
        return regionRepository.searchByLocationName(locationName).stream()
                .sorted(Comparator.comparing(Region::getLocationName))
                .toList();
    }
}
