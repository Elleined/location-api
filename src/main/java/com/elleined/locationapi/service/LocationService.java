package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.*;
import com.elleined.locationapi.exception.EmptyUUIDException;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.mapper.*;
import com.elleined.locationapi.model.User;
import com.elleined.locationapi.model.address.Address;
import com.elleined.locationapi.model.address.DeliveryAddress;
import com.elleined.locationapi.model.address.UserAddress;
import com.elleined.locationapi.model.location.Baranggay;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import com.elleined.locationapi.utility.StringValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

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

    public AddressDTO saveUserAddress(String UUID, AddressDTO addressDTO)
            throws ResourceNotFoundException, EmptyUUIDException {

        if (StringValidator.isNotValidBody(UUID)) throw new EmptyUUIDException("UUID cannot be blank, empty, or null!");

        User user = userService.getByUUID(UUID);
        Province province = provinceService.getById(addressDTO.getProvinceId());
        City city = cityService.getById(addressDTO.getCityId());
        Baranggay baranggay = baranggayService.getById(addressDTO.getBaranggayId());

        UserAddress userAddress = addressService.saveUserAddress(user, addressDTO.getDetails(), province, city, baranggay);
        return addressMapper.toDTO(userAddress);
    }

    public AddressDTO saveDeliveryAddress(String UUID, AddressDTO addressDTO)
            throws ResourceNotFoundException, EmptyUUIDException {

        if (StringValidator.isNotValidBody(UUID)) throw new EmptyUUIDException("UUID cannot be blank, empty, or null!");

        User user = userService.getByUUID(UUID);
        Province province = provinceService.getById(addressDTO.getProvinceId());
        City city = cityService.getById(addressDTO.getCityId());
        Baranggay baranggay = baranggayService.getById(addressDTO.getBaranggayId());

        DeliveryAddress deliveryAddress = addressService.saveDeliveryAddress(user, addressDTO.getDetails(), province, city, baranggay);
        return addressMapper.toDTO(deliveryAddress);
    }

    public AddressDTO getAddress(String currentUserUUID)
            throws ResourceNotFoundException, EmptyUUIDException {

        if (StringValidator.isNotValidBody(currentUserUUID)) throw new EmptyUUIDException("UUID cannot be blank, empty, or null!");
        User user = userService.getByUUID(currentUserUUID);
        Address address = user.getUserAddress();
        return addressMapper.toDTO(address);
    }

    public Set<AddressDTO> getDeliveryAddresses(String currentUserUUID)
            throws ResourceNotFoundException, EmptyUUIDException {

        if (StringValidator.isNotValidBody(currentUserUUID)) throw new EmptyUUIDException("UUID cannot be blank, empty, or null!");
        User user = userService.getByUUID(currentUserUUID);
        return user.getDeliveryAddresses().stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public UserDTO saveUser(String UUID) throws EmptyUUIDException {
        if (StringValidator.isNotValidBody(UUID)) throw new EmptyUUIDException("UUID cannot be blank, empty, or null!");
        User user = userService.save(UUID);
        return userMapper.toDTO(user);
    }

    public UserDTO getById(int userId) throws ResourceNotFoundException {
        User user = userService.getById(userId);
        return userMapper.toDTO(user);
    }

    public UserDTO getByUUID(String UUID)
            throws ResourceNotFoundException, EmptyUUIDException {

        if (StringValidator.isNotValidBody(UUID)) throw new EmptyUUIDException("UUID cannot be blank, empty, or null!");
        User user = userService.getByUUID(UUID);
        return userMapper.toDTO(user);
    }
}
