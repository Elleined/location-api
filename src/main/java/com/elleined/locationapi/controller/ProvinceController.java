package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provinces")
@RequiredArgsConstructor
public class ProvinceController {

    private final LocationService locationService;

    @GetMapping
    public List<ProvinceDTO> getAll() {
        return locationService.getAllProvince();
    }

    @GetMapping("/getAllByRegionId/{regionId}")
    public List<ProvinceDTO> getAllByRegionId(@PathVariable("regionId") int regionId) {
        return locationService.getAllByRegionId(regionId);
    }

    @GetMapping("/{id}")
    public ProvinceDTO getById(@PathVariable("id") int id) {
        return locationService.getProvinceById(id);
    }

    @PostMapping
    public ProvinceDTO save(@Valid @RequestBody ProvinceDTO provinceDTO) {
        return locationService.saveProvince(provinceDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProvinceDTO> delete(@PathVariable("id") int id) {
        locationService.deleteProvince(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ProvinceDTO update(@PathVariable("id") int id,
                              @Valid @RequestBody ProvinceDTO provinceDTO) {
        return locationService.updateProvince(id, provinceDTO);
    }

}
