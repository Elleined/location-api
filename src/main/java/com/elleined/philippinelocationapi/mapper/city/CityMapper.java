package com.elleined.philippinelocationapi.mapper.city;

import com.elleined.philippinelocationapi.dto.city.CityDTO;
import com.elleined.philippinelocationapi.mapper.CustomMapper;
import com.elleined.philippinelocationapi.mapper.province.ProvinceMapper;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = ProvinceMapper.class
)
public interface CityMapper extends CustomMapper<City, CityDTO> {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "provinceDTO", source = "province")
    })
    CityDTO toDTO(City city);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "province", source = "province"),
            @Mapping(target = "baranggays", expression = "java(new java.util.HashSet<>())"),
    })
    City toEntity(String name,
                  Province province);
}
