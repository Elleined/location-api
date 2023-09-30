package com.elleined.locationapi.populator;

import com.elleined.locationapi.dto.RegionDTO;
import com.elleined.locationapi.service.region.RegionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, RegionDTO.class);

        List<RegionDTO> regions = objectMapper.readValue(resource.getFile(), type);
        regionService.saveAll(regions);
    }
}
