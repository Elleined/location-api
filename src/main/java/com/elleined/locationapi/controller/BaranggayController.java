package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.BaranggayDTO;
import com.elleined.locationapi.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/baranggays")
@RequiredArgsConstructor
public class BaranggayController {
    private final LocationService locationService;

    @PostMapping
    public BaranggayDTO save(@Valid @RequestBody BaranggayDTO baranggayDTO) {
        return locationService.saveBaranggay(baranggayDTO);
    }

    @PostMapping("/saveAll")
    public Set<BaranggayDTO> saveAll(@Valid @RequestBody Set<BaranggayDTO> baranggayDTOS) {
        return locationService.saveAllBaranggay(baranggayDTOS);
    }

    @GetMapping("/{id}")
    public BaranggayDTO getById(@PathVariable("id") int id) {
        return locationService.getBaranggayById(id);
    }

    @GetMapping("/getAllByCity/{cityId}")
    public List<BaranggayDTO> getAllByCity(@PathVariable("cityId") int cityId) {
        return locationService.getAllByCity(cityId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaranggayDTO> delete(@PathVariable("id") int id) {
        locationService.deleteBaranggay(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public BaranggayDTO update(@PathVariable("id") int id,
                               @Valid @RequestBody BaranggayDTO baranggayDTO) {
        return locationService.updateBaranggay(id, baranggayDTO);
    }
}
