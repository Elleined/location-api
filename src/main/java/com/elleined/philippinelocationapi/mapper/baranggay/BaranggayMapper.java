package com.elleined.philippinelocationapi.mapper.baranggay;

import com.elleined.philippinelocationapi.dto.baranggay.BaranggayDTO;
import com.elleined.philippinelocationapi.mapper.CustomMapper;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.request.baranggay.BaranggayRequest;
import com.elleined.philippinelocationapi.service.city.CityService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BaranggayMapper implements CustomMapper<Baranggay, BaranggayDTO, BaranggayRequest> {

    protected CityService cityService;

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "cityId", source = "city.id"),
            @Mapping(target = "provinceId", source = "city.province.id"),
            @Mapping(target = "regionId", source = "city.province.region.id")
    })
    public abstract BaranggayDTO toDTO(Baranggay baranggay);

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "city", expression = "java(cityService.getById(baranggayRequest.getCityId()))"),
    })
    public abstract Baranggay toEntity(BaranggayRequest baranggayRequest);

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }
}
