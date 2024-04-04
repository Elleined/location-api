package com.elleined.philippinelocationapi.mapper.city;

import com.elleined.philippinelocationapi.dto.city.CityDTO;
import com.elleined.philippinelocationapi.mapper.CustomMapper;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.request.city.CityRequest;
import com.elleined.philippinelocationapi.service.city.CityService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CityMapper implements CustomMapper<City, CityDTO, CityRequest> {

    protected CityService cityService;

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "provinceId", source = "province.id"),
            @Mapping(target = "regionId", source = "province.region.id"),
            @Mapping(target = "baranggayIds", expression = "java(city.getAllBaranggayIds())"),
    })
    public abstract CityDTO toDTO(City city);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "province", expression = "java(cityService.getById(cityRequest.getProvinceId()))"),
            @Mapping(target = "baranggays", expression = "java(new java.util.HashSet<>())"),
    })
    public abstract City toEntity(CityRequest cityRequest);

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }
}
