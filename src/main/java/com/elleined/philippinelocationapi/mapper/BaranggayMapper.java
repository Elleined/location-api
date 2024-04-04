package com.elleined.philippinelocationapi.mapper;

import com.elleined.philippinelocationapi.dto.baranggay.BaranggayDTO;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class BaranggayMapper {


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
            @Mapping(target = "city", expression = "java(city)"),
            @Mapping(target = "locationName", source = "baranggayDTO.name")
    })
    public abstract Baranggay toEntity(BaranggayDTO baranggayDTO,
                                       @Context City city);
}
