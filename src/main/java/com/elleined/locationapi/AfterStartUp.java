package com.elleined.locationapi;

import com.elleined.locationapi.populator.BaranggayPopulator;
import com.elleined.locationapi.populator.CityPopulator;
import com.elleined.locationapi.populator.ProvincePopulator;
import com.elleined.locationapi.populator.RegionPopulator;
import com.elleined.locationapi.service.LocationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AfterStartUp {

    private final RegionPopulator regionPopulator;
    private final ProvincePopulator provincePopulator;
    private final CityPopulator cityPopulator;
    private final BaranggayPopulator baranggayPopulator;
    private final LocationService locationService;

    private static final String regionsJSONFilePath = "/json/regions.json";
    private static final String provincesJSONFilePath = "/json/provinces.json";
    private static final String citiesJSONFilePath = "/json/cities.json";
    private static final String baranggaysJSONFilePath = "/json/baranggays.json";

    @PostConstruct
    @Transactional
    void init() throws IOException {
        if (!locationService.getAllRegion().isEmpty()) {
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
