package com.elleined.locationapi.mapper;

import com.elleined.locationapi.dto.BaranggayDTO;
import com.elleined.locationapi.model.location.Baranggay;
import com.elleined.locationapi.service.CityService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BaranggayMapper {

    @Autowired
    protected CityService cityService;

    @Mappings({
            @Mapping(target = "name", source = "baranggay.locationName"),

            @Mapping(target = "cityId", source = "baranggay.city.id"),
            @Mapping(target = "cityName", source = "baranggay.city.locationName"),

            @Mapping(target = "provinceId", source = "baranggay.city.province.id"),
            @Mapping(target = "provinceName", source = "baranggay.city.province.locationName"),

            @Mapping(target = "regionId", source = "baranggay.city.province.region.id"),
            @Mapping(target = "regionName", source = "baranggay.city.province.region.locationName")
    })
    public abstract BaranggayDTO toDTO(Baranggay baranggay);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "city", expression = "java(cityService.getById(baranggayDTO.getCityId()))"),
            @Mapping(target = "locationName", source = "baranggayDTO.name")
    })
    public abstract Baranggay toEntity(BaranggayDTO baranggayDTO);
}
