package com.elleined.philippinelocationapi.populator;

import com.elleined.philippinelocationapi.mapper.province.ProvinceMapper;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.province.ProvinceRepository;
import com.elleined.philippinelocationapi.request.province.ProvinceRequest;
import com.elleined.philippinelocationapi.service.region.RegionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Transactional
public class ProvincePopulator extends Populator {
    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;

    private final RegionService regionService;

    public ProvincePopulator(ObjectMapper objectMapper, ProvinceRepository provinceRepository, ProvinceMapper provinceMapper, RegionService regionService) {
        super(objectMapper);
        this.provinceRepository = provinceRepository;
        this.provinceMapper = provinceMapper;
        this.regionService = regionService;
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, ProvinceRequest.class);

        List<ProvinceRequest> provinceRequests = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        List<Province> provinces = provinceRequests.stream()
                .map(request -> {
                    Region region = regionService.getById(request.getRegionId());
                    return provinceMapper.toEntity(region.getName(), region);
                }).toList();

        provinceRepository.saveAll(provinces);
    }
}
