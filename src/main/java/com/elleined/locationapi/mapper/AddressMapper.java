package com.elleined.locationapi.mapper;

import com.elleined.locationapi.dto.AddressDTO;
import com.elleined.locationapi.model.User;
import com.elleined.locationapi.model.address.Address;
import com.elleined.locationapi.model.address.DeliveryAddress;
import com.elleined.locationapi.model.address.UserAddress;
import com.elleined.locationapi.service.BaranggayService;
import com.elleined.locationapi.service.CityService;
import com.elleined.locationapi.service.ProvinceService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {

    @Autowired @Lazy
    protected ProvinceService provinceService;

    @Autowired @Lazy
    protected CityService cityService;

    @Autowired @Lazy
    protected BaranggayService baranggayService;

    @Mappings({
            @Mapping(target = "provinceId", source = "address.province.id"),
            @Mapping(target = "provinceName", source = "address.province.locationName"),

            @Mapping(target = "cityId", source = "address.city.id"),
            @Mapping(target = "cityName", source = "address.city.locationName"),

            @Mapping(target = "baranggayId", source = "address.baranggay.id"),
            @Mapping(target = "baranggayName", source = "address.baranggay.locationName")
    })
    public abstract AddressDTO toDTO(Address address);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "province", expression = "java(provinceService.getById(addressDTO.getProvinceId()))"),
            @Mapping(target = "city", expression = "java(cityService.getById(addressDTO.getCityId()))"),
            @Mapping(target = "baranggay", expression = "java(baranggayService.getById(addressDTO.getBaranggayId()))"),
            @Mapping(target = "user", expression = "java(registeringUser)")
    })
    public abstract UserAddress toUserAddressEntity(AddressDTO addressDTO, @Context User registeringUser);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "province", expression = "java(provinceService.getById(addressDTO.getProvinceId()))"),
            @Mapping(target = "city", expression = "java(cityService.getById(addressDTO.getCityId()))"),
            @Mapping(target = "baranggay", expression = "java(baranggayService.getById(addressDTO.getBaranggayId()))"),
            @Mapping(target = "user", expression = "java(registeringUser)")
    })
    public abstract DeliveryAddress toDeliveryAddressEntity(AddressDTO addressDTO, @Context User registeringUser);

    protected AddressDTO deliveryAddressToDTO(DeliveryAddress deliveryAddress) {
        return toDTO(deliveryAddress);
    }
}
