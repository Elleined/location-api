package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.RegionDTO;
import com.elleined.locationapi.mapper.RegionMapper;
import com.elleined.locationapi.model.Region;
import com.elleined.locationapi.service.region.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;
    private final RegionMapper regionMapper;

    @GetMapping
    public List<RegionDTO> getAll() {
        return regionService.getAll().stream()
                .map(regionMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public RegionDTO getById(@PathVariable("id") int id) {
        Region region = regionService.getById(id);
        return regionMapper.toDTO(region);
    }

    @GetMapping("/searchByLocationName")
    public List<RegionDTO> searchByLocationName(@RequestParam("locationName") String locationName) {
        return regionService.searchByLocationName(locationName).stream()
                .map(regionMapper::toDTO)
                .toList();
    }
}
