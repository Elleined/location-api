package com.elleined.philippinelocationapi.populator;

import com.elleined.philippinelocationapi.mapper.city.CityMapper;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.repository.city.CityRepository;
import com.elleined.philippinelocationapi.request.city.CityRequest;
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
@Qualifier("cityPopulator")
@Transactional
public class CityPopulator extends Populator {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    private final ProvinceService provinceService;

    public CityPopulator(ObjectMapper objectMapper, CityRepository cityRepository, CityMapper cityMapper, ProvinceService provinceService) {
        super(objectMapper);
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.provinceService = provinceService;
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, CityRequest.class);

        List<CityRequest> cityRequests = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        List<City> cities = cityRequests.stream()
                .map(request -> {
                    Province province = provinceService.getById(request.getProvinceId());
                    return cityMapper.toEntity(request.getName(), province);
                })
                .toList();

        cityRepository.saveAll(cities);
    }
}
