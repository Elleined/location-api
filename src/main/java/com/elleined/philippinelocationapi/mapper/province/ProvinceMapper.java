package com.elleined.philippinelocationapi.mapper.province;


import com.elleined.philippinelocationapi.dto.province.ProvinceDTO;
import com.elleined.philippinelocationapi.mapper.CustomMapper;
import com.elleined.philippinelocationapi.mapper.region.RegionMapper;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = RegionMapper.class
)
public interface ProvinceMapper extends CustomMapper<Province, ProvinceDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "regionDTO", source = "region")
    })
    ProvinceDTO toDTO(Province province);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "region", source = "region"),
            @Mapping(target = "cities", expression = "java(new java.util.HashSet<>())")
    })
    Province toEntity(String name,
                      Region region);
}
