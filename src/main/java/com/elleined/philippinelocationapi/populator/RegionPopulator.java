package com.elleined.philippinelocationapi.populator;

import com.elleined.philippinelocationapi.dto.RegionDTO;
import com.elleined.philippinelocationapi.service.region.RegionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Primary
@Transactional
public class RegionPopulator extends Populator {
    private final RegionService regionService;

    protected RegionPopulator(ObjectMapper objectMapper, RegionService regionService) {
        super(objectMapper);
        this.regionService = regionService;
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, RegionDTO.class);

        List<RegionDTO> regions = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        regionService.saveAll(regions);
    }
}
