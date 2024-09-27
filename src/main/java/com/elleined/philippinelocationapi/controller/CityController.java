package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.city.CityDTO;
import com.elleined.philippinelocationapi.mapper.city.CityMapper;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.city.CityService;
import com.elleined.philippinelocationapi.service.province.ProvinceService;
import com.elleined.philippinelocationapi.service.region.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities")
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    private final RegionService regionService;
    private final ProvinceService provinceService;

    @GetMapping("/paged")
    public Page<CityDTO> getAllBy(@PathVariable("regionId") int regionId,
                                  @PathVariable("provinceId") int provinceId,
                                  @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                  @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                  @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                  @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy) {

        Region region = regionService.getById(regionId);
        Province province = provinceService.getById(provinceId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return cityService.getAllBy(region, province, pageable)
                .map(cityMapper::toDTO);
    }

    @GetMapping
    public List<CityDTO> getAllBy(@PathVariable("regionId") int regionId,
                                  @PathVariable("provinceId") int provinceId) {

        Region region = regionService.getById(regionId);
        Province province = provinceService.getById(provinceId);

        return cityService.getAllBy(region, province).stream()
                .map(cityMapper::toDTO)
                .toList();
    }

    @GetMapping("/search")
    public Page<CityDTO> findAllByName(@PathVariable("regionId") int regionId,
                                       @PathVariable("provinceId") int provinceId,
                                       @RequestParam("name") String name,
                                       @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                       @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                       @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                       @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy) {

        Region region = regionService.getById(regionId);
        Province province = provinceService.getById(provinceId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return cityService.findAllByName(region, province, name, pageable)
                .map(cityMapper::toDTO);
    }
}
