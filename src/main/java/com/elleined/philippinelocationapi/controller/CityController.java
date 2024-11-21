package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.city.CityDTO;
import com.elleined.philippinelocationapi.mapper.city.CityMapper;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.city.CityService;
import com.elleined.philippinelocationapi.service.province.ProvinceService;
import com.elleined.philippinelocationapi.service.region.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities")
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    private final RegionService regionService;
    private final ProvinceService provinceService;

    @GetMapping
    public List<CityDTO> getAllBy(@PathVariable("regionId") int regionId,
                                  @PathVariable("provinceId") int provinceId) {

        Region region = regionService.getById(regionId);
        Province province = provinceService.getById(region, provinceId);

        return cityService.getAllBy(region, province).stream()
                .map(cityMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public CityDTO getById(@PathVariable("regionId") int regionId,
                           @PathVariable("provinceId") int provinceId,
                           @PathVariable("id") int id) {

        Region region = regionService.getById(regionId);
        Province province = provinceService.getById(region, provinceId);

        City city = cityService.getById(region, province, id);
        return cityMapper.toDTO(city);
    }
}
