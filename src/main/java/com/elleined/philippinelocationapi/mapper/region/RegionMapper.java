package com.elleined.philippinelocationapi.mapper.region;

import com.elleined.philippinelocationapi.dto.region.RegionDTO;
import com.elleined.philippinelocationapi.mapper.CustomMapper;
import com.elleined.philippinelocationapi.model.region.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RegionMapper extends CustomMapper<Region, RegionDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description")
    })
    RegionDTO toDTO(Region region);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "provinces", expression = "java(new java.util.HashSet<>())")
    })
    Region toEntity(String name,
                    String description);
}
