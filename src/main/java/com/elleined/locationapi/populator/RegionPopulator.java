package com.elleined.locationapi.populator;

import com.elleined.locationapi.dto.RegionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Set;

@Component
@Primary
@Transactional
public class RegionPopulator extends Populator {

    public RegionPopulator(ObjectMapper objectMapper, LocationService locationService) {
        super(objectMapper, locationService);
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        var type = objectMapper.getTypeFactory().constructCollectionType(Set.class, RegionDTO.class);

        Set<RegionDTO> regions = objectMapper.readValue(resource.getFile(), type);
        locationService.saveAllRegion(regions);
    }
}
