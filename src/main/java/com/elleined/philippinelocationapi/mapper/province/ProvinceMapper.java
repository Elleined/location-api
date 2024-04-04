package com.elleined.philippinelocationapi.mapper.province;


import com.elleined.philippinelocationapi.dto.province.ProvinceDTO;
import com.elleined.philippinelocationapi.mapper.CustomMapper;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.request.province.ProvinceRequest;
import com.elleined.philippinelocationapi.service.region.RegionService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProvinceMapper implements CustomMapper<Province, ProvinceDTO, ProvinceRequest> {

    protected RegionService regionService;

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "regionId", source = "region.id"),
            @Mapping(target = "cityIds", expression = "java(province.getAllCityIds())")
    })
    public abstract ProvinceDTO toDTO(Province province);

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "region", expression = "java(regionService.getById(provinceRequest.getRegionId()))"),
            @Mapping(target = "cities", expression = "java(new java.util.HashSet<>())")
    })
    public abstract Province toEntity(ProvinceRequest provinceRequest);

    @Autowired
    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }
}
