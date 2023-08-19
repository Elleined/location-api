package com.elleined.locationapi.populator;

import com.elleined.locationapi.dto.BaranggayDTO;
import com.elleined.locationapi.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Set;

@Component
@Qualifier("baranggayPopulator")
@Transactional
public class BaranggayPopulator extends Populator {
    public BaranggayPopulator(ObjectMapper objectMapper, LocationService locationService) {
        super(objectMapper, locationService);
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        var type = objectMapper.getTypeFactory().constructCollectionType(Set.class, BaranggayDTO.class);

        Set<BaranggayDTO> baranggays = objectMapper.readValue(resource.getFile(), type);
        locationService.saveAllBaranggay(baranggays);
    }
}
