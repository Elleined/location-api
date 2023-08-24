package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.BaranggayDTO;
import com.elleined.locationapi.dto.CityDTO;
import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.dto.RegionDTO;
import com.elleined.locationapi.exception.AlreadyExistsException;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.mapper.BaranggayMapper;
import com.elleined.locationapi.mapper.CityMapper;
import com.elleined.locationapi.mapper.ProvinceMapper;
import com.elleined.locationapi.mapper.RegionMapper;
import com.elleined.locationapi.model.Baranggay;
import com.elleined.locationapi.model.City;
import com.elleined.locationapi.model.Province;
import com.elleined.locationapi.model.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
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

    private final RegionMapper regionMapper;
    private final ProvinceMapper provinceMapper;
    private final CityMapper cityMapper;
    private final BaranggayMapper baranggayMapper;

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
                .collect(Collectors.toUnmodifiableSet());
        regionService.saveAll(regions);

        return regions.stream()
                .map(regionMapper::toDTO)
                .collect(Collectors.toUnmodifiableSet());
    }

    public RegionDTO getRegionById(int regionId) throws ResourceNotFoundException {
        Region region = regionService.getById(regionId);
        return regionMapper.toDTO(region);
    }

    public Set<RegionDTO> getAllRegion() throws ResourceNotFoundException {
        return regionService.getAll().stream()
                .sorted(Comparator.comparing(Region::getLocationName))
                .map(regionMapper::toDTO)
                .collect(Collectors.toUnmodifiableSet());
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
                .sorted()
                .map(provinceMapper::toEntity)
                .collect(Collectors.toUnmodifiableSet());
        provinceService.saveAll(provinces);

        return provinces.stream()
                .map(provinceMapper::toDTO)
                .collect(Collectors.toUnmodifiableSet());
    }

    public ProvinceDTO getProvinceById(int provinceId) throws ResourceNotFoundException {
        Province province = provinceService.getById(provinceId);
        return provinceMapper.toDTO(province);
    }

    public Set<ProvinceDTO> getAllByRegion(int regionId) throws ResourceNotFoundException {
        Region region = regionService.getById(regionId);
        return region.getProvinces().stream()
                .sorted(Comparator.comparing(Province::getLocationName))
                .map(provinceMapper::toDTO)
                .collect(Collectors.toUnmodifiableSet());
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
                .collect(Collectors.toUnmodifiableSet());
        cityService.saveAll(cities);

        return cities.stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toUnmodifiableSet());
    }

    public CityDTO getCityById(int cityId) throws ResourceNotFoundException {
        City city = cityService.getById(cityId);
        return cityMapper.toDTO(city);
    }

    public Set<CityDTO> getAllByProvince(int provinceId) throws ResourceNotFoundException {
        Province province = provinceService.getById(provinceId);
        return province.getCities().stream()
                .sorted(Comparator.comparing(City::getLocationName))
                .map(cityMapper::toDTO)
                .collect(Collectors.toUnmodifiableSet());
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
                .collect(Collectors.toUnmodifiableSet());
        baranggayService.saveAll(baranggays);

        return baranggays.stream()
                .map(baranggayMapper::toDTO)
                .collect(Collectors.toUnmodifiableSet());
    }

    public BaranggayDTO getBaranggayById(int baranggayId) throws ResourceNotFoundException {
        Baranggay baranggay = baranggayService.getById(baranggayId);
        return baranggayMapper.toDTO(baranggay);
    }

    public Set<BaranggayDTO> getAllByCity(int cityId) throws ResourceNotFoundException {
        City city = cityService.getById(cityId);
        return city.getBaranggays().stream()
                .sorted(Comparator.comparing(Baranggay::getLocationName))
                .map(baranggayMapper::toDTO)
                .collect(Collectors.toUnmodifiableSet());
    }

    public List<RegionDTO> searchByRegionName(String locationName) {
        return regionService.searchByLocationName(locationName).stream()
                .map(regionMapper::toDTO)
                .toList();
    }

    public List<ProvinceDTO> searchByProvinceName(String locationName) {
        return provinceService.searchByLocationName(locationName).stream()
                .sorted(Comparator.comparing(Province::getLocationName))
                .map(provinceMapper::toDTO)
                .toList();
    }

    public List<CityDTO> searchByCityName(String locationName) {
        return cityService.searchByLocationName(locationName).stream()
                .sorted(Comparator.comparing(City::getLocationName))
                .map(cityMapper::toDTO)
                .toList();
    }

    public List<BaranggayDTO> searchByBaranggayName(String locationName) {
        return baranggayService.searchByLocationName(locationName).stream()
                .sorted(Comparator.comparing(Baranggay::getLocationName))
                .map(baranggayMapper::toDTO)
                .toList();
    }
}
