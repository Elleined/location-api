package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.RegionDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {
    private final LocationService locationService;

    @PostMapping
    public RegionDTO save(@Valid @RequestBody RegionDTO regionDTO) {
        return locationService.saveRegion(regionDTO);
    }

    @PostMapping("/saveAll")
    public Set<RegionDTO> saveAll(@RequestBody Set<RegionDTO> regionDTOs) {
        return locationService.saveAllRegion(regionDTOs);
    }

    @GetMapping
    public Set<RegionDTO> getAll() {
        return locationService.getAllRegion();
    }

    @GetMapping("/{id}")
    public RegionDTO getById(@PathVariable("id") int id) {
        return locationService.getRegionById(id);
    }

    @GetMapping("/searchByLocationName")
    public List<RegionDTO> searchByLocationName(@RequestParam("locationName") String locationName) {
        return locationService.searchByRegionName(locationName);
    }
}
