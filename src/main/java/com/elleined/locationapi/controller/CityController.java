package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.CityDTO;
import com.elleined.locationapi.mapper.CityMapper;
import com.elleined.locationapi.model.City;
import com.elleined.locationapi.model.Province;
import com.elleined.locationapi.service.city.CityService;
import com.elleined.locationapi.service.province.ProvinceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    private final ProvinceService provinceService;

    @PostMapping
    public CityDTO save(@Valid @RequestBody CityDTO cityDTO) {
        City city = cityService.save(cityDTO);
        return cityMapper.toDTO(city);
    }

    @PostMapping("/saveAll")
    public List<CityDTO> saveAll(@Valid @RequestBody List<CityDTO> cityDTOS) {
        return cityService.saveAll(cityDTOS).stream()
                .map(cityMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public CityDTO getById(@PathVariable("id") int id) {
        City city = cityService.getById(id);
        return cityMapper.toDTO(city);
    }

    @GetMapping("/getAllByProvince/{provinceId}")
    public List<CityDTO> getAllByProvince(@PathVariable("provinceId") int provinceId) {
        Province province = provinceService.getById(provinceId);
        return cityService.getAllBy(province).stream()
                .map(cityMapper::toDTO)
                .toList();
    }

    @GetMapping("/searchByLocationName")
    public List<CityDTO> searchByLocationName(@RequestParam("locationName") String locationName) {
        return cityService.searchByLocationName(locationName).stream()
                .map(cityMapper::toDTO)
                .toList();
    }
}
