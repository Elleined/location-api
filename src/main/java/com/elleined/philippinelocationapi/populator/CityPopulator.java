package com.elleined.philippinelocationapi.populator;

import com.elleined.philippinelocationapi.dto.CityDTO;
import com.elleined.philippinelocationapi.service.city.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Qualifier("cityPopulator")
@Transactional
public class CityPopulator extends Populator {
    private final CityService cityService;

    protected CityPopulator(ObjectMapper objectMapper, CityService cityService) {
        super(objectMapper);
        this.cityService = cityService;
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, CityDTO.class);

        List<CityDTO> cities = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        cityService.saveAll(cities);
    }
}
