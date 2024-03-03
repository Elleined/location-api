package com.elleined.locationapi;

import com.elleined.locationapi.populator.Populator;
import com.elleined.locationapi.service.region.RegionService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class AfterStartUp {

    private final Populator regionPopulator;
    private final Populator provincePopulator;
    private final Populator cityPopulator;
    private final Populator baranggayPopulator;
    private final RegionService regionService;

    public AfterStartUp(Populator regionPopulator,
                        @Qualifier("provincePopulator") Populator provincePopulator,
                        @Qualifier("cityPopulator") Populator cityPopulator,
                        @Qualifier("baranggayPopulator") Populator baranggayPopulator,
                        RegionService regionService) {

        this.regionPopulator = regionPopulator;
        this.provincePopulator = provincePopulator;
        this.cityPopulator = cityPopulator;
        this.baranggayPopulator = baranggayPopulator;
        this.regionService = regionService;

    }

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
