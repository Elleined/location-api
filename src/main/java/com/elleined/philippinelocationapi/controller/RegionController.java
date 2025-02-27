package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.region.RegionDTO;
import com.elleined.philippinelocationapi.mapper.region.RegionMapper;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.region.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
