package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.baranggay.BaranggayDTO;
import com.elleined.philippinelocationapi.mapper.baranggay.BaranggayMapper;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.baranggay.BaranggayService;
import com.elleined.philippinelocationapi.service.city.CityService;
import com.elleined.philippinelocationapi.service.province.ProvinceService;
import com.elleined.philippinelocationapi.service.region.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities/{cityId}/baranggays")
@RequiredArgsConstructor
public class BaranggayController {
    private final BaranggayService baranggayService;
    private final BaranggayMapper baranggayMapper;

    private final RegionService regionService;
    private final ProvinceService provinceService;
    private final CityService cityService;

    @GetMapping
    public Page<BaranggayDTO> getAllBy(@PathVariable("regionId") int regionId,
                                       @PathVariable("provinceId") int provinceId,
                                       @PathVariable("cityId") int cityId,
                                       @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                       @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                       @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                       @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                       @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Region region = regionService.getById(regionId);
        Province province = provinceService.getById(provinceId);
        City city = cityService.getById(cityId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return baranggayService.getAllBy(region, province, city, pageable)
                .map(baranggayMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }

    @GetMapping("/search")
    public Page<BaranggayDTO> findAllByName(@PathVariable("regionId") int regionId,
                                            @PathVariable("provinceId") int provinceId,
                                            @PathVariable("cityId") int cityId,
                                            @RequestParam("name") String name,
                                            @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                            @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                            @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                            @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                            @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Region region = regionService.getById(regionId);
        Province province = provinceService.getById(provinceId);
        City city = cityService.getById(cityId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return baranggayService.findAllByName(region, province, city, name, pageable)
                .map(baranggayMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }
}
