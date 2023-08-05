package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.*;
import com.elleined.locationapi.exception.AlreadyExistsException;
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

        User registeringUser = userService.getByUUID(UUID);
        UserAddress userAddress = addressMapper.toUserAddressEntity(addressDTO, registeringUser);
        addressService.saveUserAddress(userAddress);
        return addressMapper.toDTO(userAddress);
    }

    public AddressDTO saveDeliveryAddress(String UUID, AddressDTO addressDTO)
            throws ResourceNotFoundException, EmptyUUIDException {

        if (StringValidator.isNotValidBody(UUID)) throw new EmptyUUIDException("UUID cannot be blank, empty, or null!");

        User user = userService.getByUUID(UUID);
        DeliveryAddress deliveryAddress = addressMapper.toDeliveryAddressEntity(addressDTO, user);
        addressService.saveDeliveryAddress(deliveryAddress);
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

    public RegionDTO saveRegion(RegionDTO regionDTO)
            throws AlreadyExistsException, ResourceNotFoundException {

        if (regionService.isAlreadyExists(regionDTO)) throw new AlreadyExistsException("One of the provided id already exists!");

        Region region = regionMapper.toEntity(regionDTO);
        regionService.save(region);
        return regionMapper.toDTO(region);
    }

    public Set<RegionDTO> saveAllRegion(Set<RegionDTO> regionDTOs)
            throws AlreadyExistsException, ResourceNotFoundException {

        if (regionService.isAlreadyExists(regionDTOs)) throw new AlreadyExistsException("One of the provided id already exists!");

        Set<Region> regions = regionDTOs.stream()
                .map(regionMapper::toEntity)
                .collect(Collectors.toSet());
        regionService.saveAll(regions);

        return regions.stream()
                .map(regionMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public RegionDTO getRegionById(int regionId) throws ResourceNotFoundException {
        Region region = regionService.getById(regionId);
        return regionMapper.toDTO(region);
    }

    public Set<RegionDTO> getAllRegion() throws ResourceNotFoundException {
        return regionService.getAll().stream()
                .map(regionMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public ProvinceDTO saveProvince(ProvinceDTO provinceDTO)
            throws AlreadyExistsException, ResourceNotFoundException {

        if (provinceService.isAlreadyExists(provinceDTO)) throw new AlreadyExistsException("One of the provided id already exists!");
        Province province = provinceMapper.toEntity(provinceDTO);
        provinceService.save(province);
        return provinceMapper.toDTO(province);
    }

    public Set<ProvinceDTO> saveAllProvince(Set<ProvinceDTO> provinceDTOs)
            throws AlreadyExistsException, ResourceNotFoundException {

        if (provinceService.isAlreadyExists(provinceDTOs)) throw new AlreadyExistsException("One of the provided id already exists!");

        Set<Province> provinces = provinceDTOs.stream()
                .map(provinceMapper::toEntity)
                .collect(Collectors.toSet());
        provinceService.saveAll(provinces);

        return provinces.stream()
                .map(provinceMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public ProvinceDTO getProvinceById(int provinceId) throws ResourceNotFoundException {
        Province province = provinceService.getById(provinceId);
        return provinceMapper.toDTO(province);
    }

    public List<ProvinceDTO> getAllByRegion(int regionId) throws ResourceNotFoundException {
        Region region = regionService.getById(regionId);
        return region.getProvinces().stream()
                .map(provinceMapper::toDTO)
                .toList();
    }

    public CityDTO saveCity(CityDTO cityDTO)
            throws ResourceNotFoundException, AlreadyExistsException {

        if (cityService.isAlreadyExists(cityDTO)) throw new AlreadyExistsException("One of the provided id already exists!");
        City city = cityMapper.toEntity(cityDTO);
        cityService.save(city);
        return cityMapper.toDTO(city);
    }

    public Set<CityDTO> saveAllCities(Set<CityDTO> cityDTOS)
            throws ResourceNotFoundException, AlreadyExistsException {
        if (cityService.isAlreadyExists(cityDTOS)) throw new AlreadyExistsException("One of the provided id already exists!");

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

    public List<CityDTO> getAllByProvince(int provinceId) throws ResourceNotFoundException {
        Province province = provinceService.getById(provinceId);
        return province.getCities().stream()
                .map(cityMapper::toDTO)
                .toList();
    }

    public BaranggayDTO saveBaranggay(BaranggayDTO baranggayDTO)
            throws ResourceNotFoundException, AlreadyExistsException {

        if (baranggayService.isAlreadyExists(baranggayDTO)) throw new AlreadyExistsException("One of the provided id already exists!");
        Baranggay baranggay = baranggayMapper.toEntity(baranggayDTO);
        baranggayService.save(baranggay);
        return baranggayMapper.toDTO(baranggay);
    }

    public Set<BaranggayDTO> saveAllBaranggay(Set<BaranggayDTO> baranggayDTOS)
            throws ResourceNotFoundException, AlreadyExistsException {

        if (baranggayService.isAlreadyExists(baranggayDTOS)) throw new AlreadyExistsException("One of the provided id already exists!");
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

    public List<RegionDTO> searchByRegionName(String locationName) {
        return regionService.searchByLocationName(locationName).stream()
                .map(regionMapper::toDTO)
                .toList();
    }

    public List<ProvinceDTO> searchByProvinceName(String locationName) {
        return provinceService.searchByLocationName(locationName).stream()
                .map(provinceMapper::toDTO)
                .toList();
    }

    public List<CityDTO> searchByCityName(String locationName) {
        return cityService.searchByLocationName(locationName).stream()
                .map(cityMapper::toDTO)
                .toList();
    }

    public List<BaranggayDTO> searchByBaranggayName(String locationName) {
        return baranggayService.searchByLocationName(locationName).stream()
                .map(baranggayMapper::toDTO)
                .toList();
    }
}
