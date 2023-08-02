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

import java.util.List;
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

    public UserDTO getUserById(int userId) throws ResourceNotFoundException {
        User user = userService.getById(userId);
        return userMapper.toDTO(user);
    }

    public UserDTO getUserByUUID(String UUID)
            throws ResourceNotFoundException, EmptyUUIDException {

        if (StringValidator.isNotValidBody(UUID)) throw new EmptyUUIDException("UUID cannot be blank, empty, or null!");
        User user = userService.getByUUID(UUID);
        return userMapper.toDTO(user);
    }

    public ProvinceDTO saveProvince(ProvinceDTO provinceDTO) {
        Province province = provinceService.save(provinceDTO.getName(), provinceDTO.getRegionId());
        return provinceMapper.toDTO(province);
    }

    public ProvinceDTO getProvinceById(int provinceId) throws ResourceNotFoundException {
        Province province =  provinceService.getById(provinceId);
        return provinceMapper.toDTO(province);
    }

    public List<ProvinceDTO> getAllByRegionId(int regionId) {
        return provinceService.getAllByRegionId(regionId).stream()
                .map(provinceMapper::toDTO)
                .toList();
    }

    public List<ProvinceDTO> getAllProvince() {
        return provinceService.getAll().stream()
                .map(provinceMapper::toDTO)
                .toList();
    }

    public void deleteProvince(int provinceId) {
        provinceService.delete(provinceId);
    }

    public ProvinceDTO updateProvince(int provinceId, ProvinceDTO provinceDTO) throws ResourceNotFoundException {
        Province province = provinceService.getById(provinceId);
        provinceService.update(province, provinceDTO.getName(), provinceDTO.getRegionId());
        return provinceMapper.toDTO(province);
    }

    public CityDTO saveCity(CityDTO cityDTO) throws ResourceNotFoundException {
        Province province = provinceService.getById(cityDTO.getProvinceId());
        City city = cityService.save(province, cityDTO.getName(), cityDTO.getZipCode());
        return cityMapper.toDTO(city);
    }

    public CityDTO getCityById(int cityId) throws ResourceNotFoundException {
        City city = cityService.getById(cityId);
        return cityMapper.toDTO(city);
    }

    public CityDTO getCityByZipCode(int zipCode) throws ResourceNotFoundException {
        City city = cityService.getByZipCode(zipCode);
        return cityMapper.toDTO(city);
    }

    public List<CityDTO> getAllByProvince(int provinceId) throws ResourceNotFoundException {
        Province province = provinceService.getById(provinceId);
        return cityService.getAllByProvince(province).stream()
                .map(cityMapper::toDTO)
                .toList();
    }

    public void deleteCity(int cityId) {
        cityService.delete(cityId);
    }

    public CityDTO updateCity(int cityId, CityDTO cityDTO) throws ResourceNotFoundException {
        Province province = provinceService.getById(cityDTO.getProvinceId());
        City city = cityService.getById(cityId);
        cityService.update(city, province, cityDTO.getName(), cityDTO.getZipCode());
        return cityMapper.toDTO(city);
    }

    public BaranggayDTO saveBaranggay(BaranggayDTO baranggayDTO) throws ResourceNotFoundException {
        City city = cityService.getById(baranggayDTO.getCityId());
        Baranggay baranggay = baranggayService.save(city, baranggayDTO.getName());
        return baranggayMapper.toDTO(baranggay);
    }

    public BaranggayDTO getBaranggayById(int baranggayId) throws ResourceNotFoundException {
        Baranggay baranggay = baranggayService.getById(baranggayId);
        return baranggayMapper.toDTO(baranggay);
    }

    public List<BaranggayDTO> getAllByCity(int cityId) throws ResourceNotFoundException {
        City city = cityService.getById(cityId);
        return baranggayService.getAllByCity(city).stream()
                .map(baranggayMapper::toDTO)
                .toList();
    }

    public void deleteBaranggay(int baranggayId) {
        baranggayService.delete(baranggayId);
    }

    public BaranggayDTO updateBaranggay(int baranggayId, BaranggayDTO baranggayDTO) {
        City city = cityService.getById(baranggayDTO.getCityId());
        Baranggay baranggay = baranggayService.getById(baranggayId);
        baranggayService.update(baranggay, city, baranggayDTO.getName());
        return baranggayMapper.toDTO(baranggay);
    }
}
