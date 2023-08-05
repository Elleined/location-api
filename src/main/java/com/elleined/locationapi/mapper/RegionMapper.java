package com.elleined.locationapi.mapper;

import com.elleined.locationapi.dto.RegionDTO;
import com.elleined.locationapi.model.location.Region;
import com.elleined.locationapi.service.RegionService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class RegionMapper {

    @Autowired @Lazy
    protected RegionService regionService;

    @Mappings({
            @Mapping(target = "name", source = "region.locationName"),
            @Mapping(target = "provinceCount", expression = "java(region.getProvinces().size())"),
            @Mapping(target = "cityCount", expression = "java(regionService.getCityCount(region))"),
            @Mapping(target = "baranggayCount", expression = "java(regionService.getBaranggayCount(region))")
    })
    public abstract RegionDTO toDTO(Region region);
}
