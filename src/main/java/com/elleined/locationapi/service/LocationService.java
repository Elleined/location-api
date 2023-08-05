package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.*;
import com.elleined.locationapi.exception.EmptyUUIDException;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.exception.ZipCodeAlreadyExistsException;
import com.elleined.locationapi.mapper.*;
import com.elleined.locationapi.model.User;
import com.elleined.locationapi.model.address.Address;
import com.elleined.locationapi.model.address.DeliveryAddress;
import com.elleined.locationapi.model.address.UserAddress;
import com.elleined.locationapi.model.location.Baranggay;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import com.elleined.locationapi.model.location.Region;
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
    private final RegionService regionService;
    private final ProvinceService provinceService;
    private final CityService cityService;
    private final BaranggayService baranggayService;
    private final UserService userService;
    private final AddressService addressService;

    private final RegionMapper regionMapper;
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

    public RegionDTO saveRegion(RegionDTO regionDTO) {
        Region region = regionMapper.toEntity(regionDTO);
        regionService.save(region);
        return regionMapper.toDTO(region);
    }

    public Set<RegionDTO> saveAllRegion(Set<RegionDTO> regionDTOs) {
        Set<Region> regions = regionDTOs.stream()
                .map(regionMapper::toEntity)
                .collect(Collectors.toSet());
        regionService.saveAll(regions);

        return regions.stream()
                .map(regionMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public RegionDTO getRegionById(int regionId) {
        Region region = regionService.getById(regionId);
        return regionMapper.toDTO(region);
    }

    public Set<RegionDTO> getAllRegion() {
        return regionService.getAll().stream()
                .map(regionMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public ProvinceDTO saveProvince(ProvinceDTO provinceDTO) {
        Province province = provinceMapper.toEntity(provinceDTO);
        provinceService.save(province);
        return provinceMapper.toDTO(province);
    }

    public Set<ProvinceDTO> saveAllProvince(Set<ProvinceDTO> provinceDTOs) {
        Set<Province> provinces = provinceDTOs.stream()
                .map(provinceMapper::toEntity)
                .collect(Collectors.toSet());
        provinceService.saveAll(provinces);

        return provinces.stream()
                .map(provinceMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public ProvinceDTO getProvinceById(int provinceId) throws ResourceNotFoundException {
        Province province =  provinceService.getById(provinceId);
        return provinceMapper.toDTO(province);
    }

    public List<ProvinceDTO> getAllByRegion(int regionId) {
        Region region = regionService.getById(regionId);
        return region.getProvinces().stream()
                .map(provinceMapper::toDTO)
                .toList();
    }

    public CityDTO saveCity(CityDTO cityDTO)
            throws ResourceNotFoundException, ZipCodeAlreadyExistsException {

        City city = cityMapper.toEntity(cityDTO);
        if (cityService.isZipCodeAlreadyExists(cityDTO.getZipCode())) throw new ZipCodeAlreadyExistsException("City with zip code of " + cityDTO.getZipCode() + " already exists!");

        cityService.save(city);
        return cityMapper.toDTO(city);
    }

    public Set<CityDTO> saveAllCities(Set<CityDTO> cityDTOS) {
        Set<City> cities = cityDTOS.stream()
                .map(cityMapper::toEntity)
                .collect(Collectors.toSet());
        cityService.saveAll(cities);

        return cities.stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toSet());
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
        return province.getCities().stream()
                .map(cityMapper::toDTO)
                .toList();
    }

    public BaranggayDTO saveBaranggay(BaranggayDTO baranggayDTO) throws ResourceNotFoundException {
        Baranggay baranggay = baranggayMapper.toEntity(baranggayDTO);
        baranggayService.save(baranggay);
        return baranggayMapper.toDTO(baranggay);
    }

    public Set<BaranggayDTO> saveAllBaranggay(Set<BaranggayDTO> baranggayDTOS) {
        Set<Baranggay> baranggays = baranggayDTOS.stream()
                .map(baranggayMapper::toEntity)
                .collect(Collectors.toSet());
        baranggayService.saveAll(baranggays);

        return baranggays.stream()
                .map(baranggayMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public BaranggayDTO getBaranggayById(int baranggayId) throws ResourceNotFoundException {
        Baranggay baranggay = baranggayService.getById(baranggayId);
        return baranggayMapper.toDTO(baranggay);
    }

    public List<BaranggayDTO> getAllByCity(int cityId) throws ResourceNotFoundException {
        City city = cityService.getById(cityId);
        return city.getBaranggays().stream()
                .map(baranggayMapper::toDTO)
                .toList();
    }
}
