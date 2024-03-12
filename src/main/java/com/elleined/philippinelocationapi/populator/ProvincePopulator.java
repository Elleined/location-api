package com.elleined.philippinelocationapi.populator;

import com.elleined.philippinelocationapi.dto.ProvinceDTO;
import com.elleined.philippinelocationapi.service.province.ProvinceService;
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
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, ProvinceDTO.class);

        List<ProvinceDTO> provinces = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        provinceService.saveAll(provinces);
    }
}
