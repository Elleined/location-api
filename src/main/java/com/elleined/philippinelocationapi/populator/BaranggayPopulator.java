package com.elleined.philippinelocationapi.populator;

import com.elleined.philippinelocationapi.mapper.baranggay.BaranggayMapper;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.repository.baranggay.BaranggayRepository;
import com.elleined.philippinelocationapi.request.baranggay.BaranggayRequest;
import com.elleined.philippinelocationapi.service.city.CityService;
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
public class BaranggayPopulator extends Populator {
    private final BaranggayRepository baranggayRepository;
    private final BaranggayMapper baranggayMapper;

    private final CityService cityService;

    public BaranggayPopulator(ObjectMapper objectMapper,
                              BaranggayRepository baranggayRepository,
                              BaranggayMapper baranggayMapper,
                              CityService cityService) {

        super(objectMapper);
        this.baranggayRepository = baranggayRepository;
        this.baranggayMapper = baranggayMapper;
        this.cityService = cityService;
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, BaranggayRequest.class);

        List<BaranggayRequest> baranggayRequests = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        List<Baranggay> baranggays = baranggayRequests.stream()
                .map(request -> {
                    City city = cityService.getById(request.getCityId());
                    return baranggayMapper.toEntity(request.getName(), city);
                })
                .toList();

        baranggayRepository.saveAll(baranggays);
    }
}
