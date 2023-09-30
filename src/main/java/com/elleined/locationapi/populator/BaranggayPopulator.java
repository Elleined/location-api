package com.elleined.locationapi.populator;

import com.elleined.locationapi.dto.BaranggayDTO;
import com.elleined.locationapi.service.baranggay.BaranggayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
@Qualifier("baranggayPopulator")
@Transactional

public class BaranggayPopulator extends Populator {
    private final BaranggayService baranggayService;

    protected BaranggayPopulator(ObjectMapper objectMapper, BaranggayService baranggayService) {
        super(objectMapper);
        this.baranggayService = baranggayService;
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, BaranggayDTO.class);

        List<BaranggayDTO> baranggays = objectMapper.readValue(resource.getFile(), type);
        baranggayService.saveAll(baranggays);
    }
}
