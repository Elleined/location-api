package com.elleined.philippinelocationapi.service.region;

import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.region.RegionRepository;
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
class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    @Override
    public Region save(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public List<Region> saveAll(List<Region> regions) {
        return regionRepository.saveAll(regions);
    }

    @Override
    public boolean existsById(int id) {
        return regionRepository.existsById(id);
    }

    @Override
    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region getById(int id) throws ResourceNotFoundException {
        return regionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Region with id of " + id + " does not exists!"));
    }

    @Override
    public List<Region> getAllById(List<Integer> ids) {
        return regionRepository.findAllById(ids);
    }

    @Override
    public List<Region> searchByName(String name) {
        return regionRepository.searchByLocationName(name).stream()
                .sorted(Comparator.comparing(Location::getName))
                .toList();
    }
}
