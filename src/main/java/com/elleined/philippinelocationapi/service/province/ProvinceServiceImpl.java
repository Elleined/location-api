package com.elleined.philippinelocationapi.service.province;

import com.elleined.philippinelocationapi.exception.resource.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.province.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Override
    public Province getById(int id) throws ResourceNotFoundException {
        return provinceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Province with id of " + id + " does not exists!"));
    }

    @Override
    public Page<Province> getAllBy(Region region, Pageable pageable) {
        return provinceRepository.findAll(region, pageable);
    }

    @Override
    public List<Province> getAllBy(Region region) {
        return provinceRepository.findAll(region);
    }

    @Override
    public Page<Province> findAllByName(Region region, String name, Pageable pageable) {
        return provinceRepository.findAllByName(region, name, pageable);
    }
}
