package com.elleined.philippinelocationapi.populator;

import com.elleined.philippinelocationapi.mapper.region.RegionMapper;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.region.RegionRepository;
import com.elleined.philippinelocationapi.request.region.RegionRequest;
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
public class RegionPopulator extends Populator {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public RegionPopulator(ObjectMapper objectMapper,
                           RegionRepository regionRepository,
                           RegionMapper regionMapper) {

        super(objectMapper);
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, RegionRequest.class);

        List<RegionRequest> regionRequests = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        List<Region> regions = regionRequests.stream()
                .map(request -> regionMapper.toEntity(request.getName(), request.getDescription()))
                .toList();

        regionRepository.saveAll(regions);
    }
}
