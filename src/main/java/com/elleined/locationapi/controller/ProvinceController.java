package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.mapper.ProvinceMapper;
import com.elleined.locationapi.model.Province;
import com.elleined.locationapi.model.Region;
import com.elleined.locationapi.service.province.ProvinceService;
import com.elleined.locationapi.service.region.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provinces")
@RequiredArgsConstructor
public class ProvinceController {
    private final ProvinceService provinceService;
    private final ProvinceMapper provinceMapper;

    private final RegionService regionService;

    @PostMapping
    public ProvinceDTO save(@Valid @RequestBody ProvinceDTO provinceDTO) {
        Province province = provinceService.save(provinceDTO);
        return provinceMapper.toDTO(province);
    }

    @PostMapping("/saveAll")
    public List<ProvinceDTO> saveAll(@Valid @RequestBody List<ProvinceDTO> provinceDTOs) {
        return provinceService.saveAll(provinceDTOs).stream()
                .map(provinceMapper::toDTO)
                .toList();
    }

    @GetMapping("/getAllByRegion/{regionId}")
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

    @GetMapping("/searchByLocationName")
    public List<ProvinceDTO> searchByLocationName(@RequestParam("locationName") String locationName) {
        return provinceService.searchByLocationName(locationName).stream()
                .map(provinceMapper::toDTO)
                .toList();
    }
}
