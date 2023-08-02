package com.elleined.locationapi.mapper;


import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.model.location.Province;
import com.elleined.locationapi.service.ProvinceService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class ProvinceMapper {

    @Autowired @Lazy
    protected ProvinceService provinceService;

    @Mappings({
            @Mapping(target = "name", source = "province.locationName"),
            @Mapping(target = "cityCount", expression = "java(provinceService.getCityCount(province))"),
            @Mapping(target = "baranggayCount", expression = "java(provinceService.getBaranggayCount(province))")
    })
    public abstract ProvinceDTO toDTO(Province province);
}
