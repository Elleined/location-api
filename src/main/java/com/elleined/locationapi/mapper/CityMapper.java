package com.elleined.locationapi.mapper;

import com.elleined.locationapi.dto.CityDTO;
import com.elleined.locationapi.model.location.Baranggay;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.service.CityService;
import com.elleined.locationapi.service.ProvinceService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class CityMapper {

    @Autowired
    protected CityService cityService;

    @Autowired
    protected ProvinceService provinceService;

    @Mappings({
            @Mapping(target = "name", source = "city.locationName"),
            @Mapping(target = "provinceName", source = "city.province.locationName"),
            @Mapping(target = "provinceId", source = "city.province.id"),

            @Mapping(target = "regionId", source = "city.province.region.id"),
            @Mapping(target = "regionName", source = "city.province.region.locationName"),

            @Mapping(target = "baranggayCount", expression = "java(city.getBaranggays().size())")
    })
    public abstract CityDTO toDTO(City city);

    @Mappings({
            @Mapping(target = "province", expression = "java(provinceService.getById(cityDTO.getProvinceId()))"),
            @Mapping(target = "locationName", source = "cityDTO.name"),
            @Mapping(target = "baranggays", expression = "java(initializeBaranggays())")
    })
    public abstract City toEntity(CityDTO cityDTO);

    protected Set<Baranggay> initializeBaranggays() {
        return new HashSet<>();
    }
}
