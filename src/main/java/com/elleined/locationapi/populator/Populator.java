package com.elleined.locationapi.populator;

import com.elleined.locationapi.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public abstract class Populator {
    protected final ObjectMapper objectMapper;
    protected final LocationService locationService;

    public Populator(ObjectMapper objectMapper, LocationService locationService) {
        this.objectMapper = objectMapper;
        this.locationService = locationService;
    }

    public abstract void populate(final String jsonFile) throws IOException;
}
