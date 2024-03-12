package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.RegionDTO;
import com.elleined.philippinelocationapi.mapper.RegionMapper;
import com.elleined.philippinelocationapi.model.Region;
import com.elleined.philippinelocationapi.service.region.RegionService;
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
