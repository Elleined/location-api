package com.elleined.philippinelocationapi.service.province;

import com.elleined.philippinelocationapi.dto.province.ProvinceDTO;
import com.elleined.philippinelocationapi.exception.AlreadyExistsException;
import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.mapper.province.ProvinceMapper;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.province.ProvinceRepository;
import com.elleined.philippinelocationapi.service.region.RegionService;
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
    private final ProvinceMapper provinceMapper;

    private final RegionService regionService;

    @Override
    public List<Province> saveAll(List<ProvinceDTO> provinceDTOS) throws AlreadyExistsException {
        if (provinceDTOS.stream().anyMatch(this::isAlreadyExists)) throw new AlreadyExistsException("Cannot save all provinces! because one of the provided provinces id already exists!");
        List<Province> provinces = provinceDTOS.stream()
                .map(provinceDTO -> {
                    Region region = regionService.getById(provinceDTO.getRegionId());
                    return provinceMapper.toEntity(provinceDTO, region);
                })
                .toList();

        provinceRepository.saveAll(provinces);
        log.debug("Saving all provinces success...");
        return provinces;
    }

    @Override
    public Province getById(int id) throws ResourceNotFoundException {
        return provinceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Province with id of " + id + " does not exists"));
    }

    @Override
    public boolean isAlreadyExists(ProvinceDTO provinceDTO) {
        return provinceRepository.existsById(provinceDTO.getId());
    }

    @Override
    public List<Province> searchByLocationName(String locationName) {
        return provinceRepository.searchByLocationName(locationName).stream()
                .sorted(Comparator.comparing(Province::getLocationName))
                .toList();
    }

    @Override
    public List<Province> getAllBy(Region region) {
        return region.getProvinces().stream()
                .sorted(Comparator.comparing(Province::getLocationName))
                .toList();
    }
}
