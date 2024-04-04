package com.elleined.philippinelocationapi.populator;

import com.elleined.philippinelocationapi.mapper.province.ProvinceMapper;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.request.province.ProvinceRequest;
import com.elleined.philippinelocationapi.service.province.ProvinceService;
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
public class ProvincePopulator extends Populator {
    private final ProvinceService provinceService;
    private final ProvinceMapper provinceMapper;

    protected ProvincePopulator(ObjectMapper objectMapper, ProvinceService provinceService, ProvinceMapper provinceMapper) {
        super(objectMapper);
        this.provinceService = provinceService;
        this.provinceMapper = provinceMapper;
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, ProvinceRequest.class);

        List<ProvinceRequest> provinceRequests = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        List<Province> provinces = provinceRequests.stream()
                .map(provinceMapper::toEntity)
                .toList();

        provinceService.saveAll(provinces);
    }
}
