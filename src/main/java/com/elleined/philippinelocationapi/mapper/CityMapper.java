package com.elleined.philippinelocationapi.mapper;

import com.elleined.philippinelocationapi.dto.CityDTO;
import com.elleined.philippinelocationapi.model.City;
import com.elleined.philippinelocationapi.model.Province;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class CityMapper {

    @Mappings({
            @Mapping(target = "name", source = "city.locationName"),
            @Mapping(target = "provinceName", source = "city.province.locationName"),
            @Mapping(target = "provinceId", source = "city.province.id"),

            @Mapping(target = "regionId", source = "city.province.region.id"),
            @Mapping(target = "regionName", source = "city.province.region.locationName"),

            @Mapping(target = "baranggayCount", expression = "java(city.getBaranggayCount())")
    })
    public abstract CityDTO toDTO(City city);

    @Mappings({
            @Mapping(target = "province", expression = "java(province)"),
            @Mapping(target = "locationName", source = "cityDTO.name"),
            @Mapping(target = "baranggays", expression = "java(new java.util.HashSet<>())")
    })
    public abstract City toEntity(CityDTO cityDTO,
                                  @Context Province province);
}
