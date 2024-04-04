package com.elleined.philippinelocationapi;

import com.elleined.philippinelocationapi.populator.*;
import com.elleined.philippinelocationapi.service.region.RegionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AfterStartUp {

    private final RegionPopulator regionPopulator;
    private final ProvincePopulator provincePopulator;
    private final CityPopulator cityPopulator;
    private final BaranggayPopulator baranggayPopulator;
    private final RegionService regionService;

    private static final String regionsJSONFilePath = "/json/regions.json";
    private static final String provincesJSONFilePath = "/json/provinces.json";
    private static final String citiesJSONFilePath = "/json/cities.json";
    private static final String baranggaysJSONFilePath = "/json/baranggays.json";

    @PostConstruct
    @Transactional
    void init() throws IOException {
        if (!regionService.getAll().isEmpty()) {
            log.debug("Returning because location values already been saved!");
            return;
        }

        System.out.println("Automatically saving the values of regions, provinces, cities, and baranggay. Please wait...");
        regionPopulator.populate(regionsJSONFilePath);
        provincePopulator.populate(provincesJSONFilePath);
        cityPopulator.populate(citiesJSONFilePath);
        baranggayPopulator.populate(baranggaysJSONFilePath);
        System.out.println("Saving automatically of regions, provinces, cities, and baranggay. SUCCESS...");
    }
}
