package com.elleined.locationapi.populator;

import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Set;

@Component
@Transactional
public class ProvincePopulator extends Populator {
    public ProvincePopulator(ObjectMapper objectMapper, LocationService locationService) {
        super(objectMapper, locationService);
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        var type = objectMapper.getTypeFactory().constructCollectionType(Set.class, ProvinceDTO.class);

        Set<ProvinceDTO> provinces = objectMapper.readValue(resource.getFile(), type);
        locationService.saveAllProvince(provinces);
    }
}
