package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.CityDTO;
import com.elleined.philippinelocationapi.mapper.CityMapper;
import com.elleined.philippinelocationapi.model.City;
import com.elleined.philippinelocationapi.model.Province;
import com.elleined.philippinelocationapi.service.city.CityService;
import com.elleined.philippinelocationapi.service.province.ProvinceService;
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
