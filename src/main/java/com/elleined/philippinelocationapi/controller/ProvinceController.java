package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.province.ProvinceDTO;
import com.elleined.philippinelocationapi.mapper.province.ProvinceMapper;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.province.ProvinceService;
import com.elleined.philippinelocationapi.service.region.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions/{regionId}/provinces")
@RequiredArgsConstructor
public class ProvinceController {
    private final ProvinceService provinceService;
    private final ProvinceMapper provinceMapper;

    private final RegionService regionService;

    @GetMapping
    public List<ProvinceDTO> getAllBy(@PathVariable("regionId") int regionId) {
        Region region = regionService.getById(regionId);
        return provinceService.getAllBy(region).stream()
                .map(provinceMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ProvinceDTO getById(@PathVariable("id") int id) {
        Province province = provinceService.getById(id);
        return provinceMapper.toDTO(province);
    }

    @GetMapping("/search")
    public List<ProvinceDTO> searchByLocationName(@RequestParam("name") String name) {
        return provinceService.searchByName(name).stream()
                .map(provinceMapper::toDTO)
                .toList();
    }
}
