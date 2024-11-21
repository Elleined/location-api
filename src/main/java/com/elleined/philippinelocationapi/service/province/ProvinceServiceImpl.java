package com.elleined.philippinelocationapi.service.province;

import com.elleined.philippinelocationapi.exception.resource.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.province.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<Province> getAllBy(Region region) {
        return provinceRepository.findAll(region);
    }

    @Override
    public Province getById(Region region, int id) {
        return provinceRepository.findById(region, id).orElseThrow(() -> new ResourceNotFoundException("Province with id of " + id + " doesn't exists!"));
    }
}
