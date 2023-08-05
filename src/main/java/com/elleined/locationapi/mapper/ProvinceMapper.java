package com.elleined.locationapi.mapper;


import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import com.elleined.locationapi.service.ProvinceService;
import com.elleined.locationapi.service.RegionService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class ProvinceMapper {

    @Autowired @Lazy
    protected RegionService regionService;

    @Autowired @Lazy
    protected ProvinceService provinceService;

    @Mappings({
            @Mapping(target = "name", source = "province.locationName"),

            @Mapping(target = "regionId", source = "province.region.id"),
            @Mapping(target = "regionName", source = "province.region.locationName"),

            @Mapping(target = "cityCount", expression = "java(province.getCities().size())"),
            @Mapping(target = "baranggayCount", expression = "java(provinceService.getBaranggayCount(province))")
    })
    public abstract ProvinceDTO toDTO(Province province);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "locationName", source = "provinceDTO.name"),
            @Mapping(target = "cities", expression = "java(initializeCities())"),
            @Mapping(target = "region", expression = "java(regionService.getById(provinceDTO.getRegionId()))")
    })
    public abstract Province toEntity(ProvinceDTO provinceDTO);

    protected Set<City> initializeCities() {
        return new HashSet<>();
    }
}
