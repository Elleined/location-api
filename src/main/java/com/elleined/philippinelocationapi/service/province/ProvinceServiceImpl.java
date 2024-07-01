package com.elleined.philippinelocationapi.service.province;

import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.province.ProvinceRepository;
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
class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Override
    public Province save(Province province) {
        return provinceRepository.save(province);
    }

    @Override
    public List<Province> saveAll(List<Province> provinces) {
        return provinceRepository.saveAll(provinces);
    }

    @Override
    public boolean existsById(int id) {
        return provinceRepository.existsById(id);
    }

    @Override
    public List<Province> getAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Province getById(int id) throws ResourceNotFoundException {
        return provinceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Province with id of " + id + " does not exists!"));
    }

    @Override
    public List<Province> getAllById(List<Integer> ids) {
        return provinceRepository.findAllById(ids);
    }

    @Override
    public List<Province> searchByName(String name) {
        return provinceRepository.findAllByName(name).stream()
                .sorted(Comparator.comparing(Location::getName))
                .toList();
    }

    @Override
    public List<Province> getAllBy(Region region) {
        return region.getProvinces().stream()
                .sorted(Comparator.comparing(Location::getName))
                .toList();
    }
}
