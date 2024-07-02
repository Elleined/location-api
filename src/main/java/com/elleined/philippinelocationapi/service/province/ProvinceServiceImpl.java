package com.elleined.philippinelocationapi.service.province;

import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.province.ProvinceRepository;
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
class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Override
    public Province getById(int id) throws ResourceNotFoundException {
        return provinceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Province with id of " + id + " does not exists!"));
    }

    @Override
    public Page<Province> findAllByName(String name, Pageable pageable) {
        return provinceRepository.findAllByName(name, pageable);
    }

    @Override
    public Page<Province> getAllBy(Region region, Pageable pageable) {
        return provinceRepository.findAll(region, pageable);
    }
}
