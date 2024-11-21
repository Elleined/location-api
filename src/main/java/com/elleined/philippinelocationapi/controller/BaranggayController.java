package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.baranggay.BaranggayDTO;
import com.elleined.philippinelocationapi.mapper.baranggay.BaranggayMapper;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.baranggay.BaranggayService;
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
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities/{cityId}/baranggays")
@RequiredArgsConstructor
public class BaranggayController {
    private final BaranggayService baranggayService;
    private final BaranggayMapper baranggayMapper;

    private final RegionService regionService;
    private final ProvinceService provinceService;
    private final CityService cityService;

    @GetMapping("/{id}")
    public BaranggayDTO getById(@PathVariable("regionId") int regionId,
                                @PathVariable("provinceId") int provinceId,
                                @PathVariable("cityId") int cityId,
                                @PathVariable("id") int id) {

        Region region = regionService.getById(regionId);
        Province province = provinceService.getById(region, provinceId);
        City city = cityService.getById(region, province, cityId);

        Baranggay baranggay = baranggayService.getById(region, province, city, id);
        return baranggayMapper.toDTO(baranggay);
    }

    @GetMapping
    public List<BaranggayDTO> getAllBy(@PathVariable("regionId") int regionId,
                                       @PathVariable("provinceId") int provinceId,
                                       @PathVariable("cityId") int cityId) {

        Region region = regionService.getById(regionId);
        Province province = provinceService.getById(region, provinceId);
        City city = cityService.getById(region, province, cityId);

        return baranggayService.getAllBy(region, province, city).stream()
                .map(baranggayMapper::toDTO)
                .toList();
    }
}
