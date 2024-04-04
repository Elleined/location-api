package com.elleined.philippinelocationapi.mapper.region;

import com.elleined.philippinelocationapi.dto.region.RegionDTO;
import com.elleined.philippinelocationapi.mapper.CustomMapper;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.request.region.RegionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class RegionMapper implements CustomMapper<Region, RegionDTO, RegionRequest> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "provinceIds", expression = "java(region.getAllProvinceIds())")
    })
    public abstract RegionDTO toDTO(Region region);

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "provinces", expression = "java(new java.util.HashSet<>())")
    })
    public abstract Region toEntity(RegionRequest regionRequest);
}
