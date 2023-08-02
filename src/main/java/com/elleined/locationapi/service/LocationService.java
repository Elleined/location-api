package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.*;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.mapper.*;
import com.elleined.locationapi.model.User;
import com.elleined.locationapi.model.address.DeliveryAddress;
import com.elleined.locationapi.model.address.UserAddress;
import com.elleined.locationapi.model.location.Baranggay;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {
    private final ProvinceService provinceService;
    private final CityService cityService;
    private final BaranggayService baranggayService;
    private final UserService userService;
    private final AddressService addressService;

    private final ProvinceMapper provinceMapper;
    private final CityMapper cityMapper;
    private final BaranggayMapper baranggayMapper;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    public ProvinceDTO saveProvince(String name, int regionId) {
        Province province = provinceService.save(name, regionId);
        return provinceMapper.toDTO(province);
    }

    public CityDTO saveCity(int provinceId, String name, int zipCode) throws ResourceNotFoundException {
        Province province = provinceService.getById(provinceId);
        City city = cityService.save(province, name, zipCode);
        return cityMapper.toDTO(city);
    }

    public BaranggayDTO saveBaranggay(int cityId, String name) throws ResourceNotFoundException {
        City city = cityService.getById(cityId);
        Baranggay baranggay = baranggayService.save(city, name);
        return baranggayMapper.toDTO(baranggay);
    }

    public UserDTO saveUser(String UUID, String details, int provinceId, int cityId, int baranggayId) throws ResourceNotFoundException {
        Province province = provinceService.getById(provinceId);
        City city = cityService.getById(cityId);
        Baranggay baranggay = baranggayService.getById(baranggayId);

        User user = userService.save(UUID, details, province, city, baranggay);
        return userMapper.toDTO(user);
    }

    public AddressDTO saveDeliveryAddress(String UUID, String details, int provinceId, int cityId, int baranggayId) throws ResourceNotFoundException {
        Province province = provinceService.getById(provinceId);
        City city = cityService.getById(cityId);
        Baranggay baranggay = baranggayService.getById(baranggayId);
        User user = userService.getByUUID(UUID);

        DeliveryAddress deliveryAddress = addressService.saveDeliveryAddress(user, details, province, city, baranggay);
        return addressMapper.toDTO(deliveryAddress);
    }
}
