package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/provinces")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProvinceController {

    private final LocationService locationService;


    @PostMapping
    public ProvinceDTO save(@Valid @RequestBody ProvinceDTO provinceDTO) {
        return locationService.saveProvince(provinceDTO);
    }

    @PostMapping("/saveAll")
    public Set<ProvinceDTO> saveAll(@Valid @RequestBody Set<ProvinceDTO> provinceDTOs) {
        return locationService.saveAllProvince(provinceDTOs);
    }

    @GetMapping("/getAllByRegion/{regionId}")
    public Set<ProvinceDTO> getAllByRegionId(@PathVariable("regionId") int regionId) {
        return locationService.getAllByRegion(regionId);
    }

    @GetMapping("/{id}")
    public ProvinceDTO getById(@PathVariable("id") int id) {
        return locationService.getProvinceById(id);
    }

    @GetMapping("/searchByLocationName")
    public List<ProvinceDTO> searchByLocationName(@RequestParam("locationName") String locationName) {
        return locationService.searchByProvinceName(locationName);
    }
}
