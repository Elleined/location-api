package com.elleined.locationapi.populator;

import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.service.province.ProvinceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
@Qualifier("provincePopulator")
@Transactional
public class ProvincePopulator extends Populator {
    private final ProvinceService provinceService;

    protected ProvincePopulator(ObjectMapper objectMapper, ProvinceService provinceService) {
        super(objectMapper);
        this.provinceService = provinceService;
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, ProvinceDTO.class);

        List<ProvinceDTO> provinces = objectMapper.readValue(resource.getFile(), type);
        provinceService.saveAll(provinces);
    }
}
