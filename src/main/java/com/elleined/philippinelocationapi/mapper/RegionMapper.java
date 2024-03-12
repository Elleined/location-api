package com.elleined.philippinelocationapi.mapper;

import com.elleined.philippinelocationapi.dto.RegionDTO;
import com.elleined.philippinelocationapi.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class RegionMapper {

    @Mappings({
            @Mapping(target = "name", source = "region.locationName"),
            @Mapping(target = "provinceCount", expression = "java(region.getProvinceCount())"),
            @Mapping(target = "cityCount", expression = "java(region.getCityCount())"),
            @Mapping(target = "baranggayCount", expression = "java(region.getBaranggayCount())")
    })
    public abstract RegionDTO toDTO(Region region);

    @Mappings({
            @Mapping(target = "locationName", source = "regionDTO.name"),
            @Mapping(target = "provinces", expression = "java(new java.util.HashSet<>())")
    })
    public abstract Region toEntity(RegionDTO regionDTO);
}
