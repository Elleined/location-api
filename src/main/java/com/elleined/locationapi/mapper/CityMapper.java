package com.elleined.locationapi.mapper;

import com.elleined.locationapi.dto.CityDTO;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.service.CityService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class CityMapper {

    @Autowired
    @Lazy
    protected CityService cityService;

    @Mappings({
            @Mapping(target = "name", source = "city.locationName"),
            @Mapping(target = "provinceName", source = "city.province.locationName"),
            @Mapping(target = "baranggayCount", expression = "java(cityService.getBaranggayCount(city))")
    })
    public abstract CityDTO toDTO(City city);
}
