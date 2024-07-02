package com.elleined.philippinelocationapi.service.region;

import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.region.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    @Override
    public Page<Region> getAll(Pageable pageable) {
        return regionRepository.findAll(pageable);
    }

    @Override
    public Region getById(int id) throws ResourceNotFoundException {
        return regionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Region with id of " + id + " does not exists!"));
    }

    @Override
    public Page<Region> findAllByName(String name, Pageable pageable) {
        return regionRepository.findAllByName(name, pageable);
    }
}
