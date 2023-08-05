package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/provinces")
@RequiredArgsConstructor
public class ProvinceController {

    private final LocationService locationService;

    @GetMapping("/getAllByRegionId/{regionId}")
    public List<ProvinceDTO> getAllByRegionId(@PathVariable("regionId") int regionId) {
        return locationService.getAllByRegion(regionId);
    }

    @GetMapping("/{id}")
    public ProvinceDTO getById(@PathVariable("id") int id) {
        return locationService.getProvinceById(id);
    }

    @PostMapping
    public ProvinceDTO save(@Valid @RequestBody ProvinceDTO provinceDTO) {
        return locationService.saveProvince(provinceDTO);
    }

    @PostMapping("/saveAll")
    public Set<ProvinceDTO> saveAll(@Valid @RequestBody Set<ProvinceDTO> provinceDTOs) {
        return locationService.saveAllProvince(provinceDTOs);
    }
}
