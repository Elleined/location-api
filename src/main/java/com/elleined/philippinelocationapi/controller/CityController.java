package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.city.CityDTO;
import com.elleined.philippinelocationapi.mapper.city.CityMapper;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.service.city.CityService;
import com.elleined.philippinelocationapi.service.province.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions/provinces/{provinceId}/cities")
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    private final ProvinceService provinceService;

    @GetMapping
    public List<CityDTO> getAllByProvince(@PathVariable("provinceId") int provinceId) {
        Province province = provinceService.getById(provinceId);
        return cityService.getAllBy(province).stream()
                .map(cityMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public CityDTO getById(@PathVariable("id") int id) {
        City city = cityService.getById(id);
        return cityMapper.toDTO(city);
    }

    @GetMapping("/search")
    public List<CityDTO> searchByLocationName(@RequestParam("name") String name) {
        return cityService.searchByName(name).stream()
                .map(cityMapper::toDTO)
                .toList();
    }
}
