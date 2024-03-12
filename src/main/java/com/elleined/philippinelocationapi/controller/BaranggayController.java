package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.BaranggayDTO;
import com.elleined.philippinelocationapi.mapper.BaranggayMapper;
import com.elleined.philippinelocationapi.model.Baranggay;
import com.elleined.philippinelocationapi.model.City;
import com.elleined.philippinelocationapi.service.baranggay.BaranggayService;
import com.elleined.philippinelocationapi.service.city.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baranggays")
@RequiredArgsConstructor
public class BaranggayController {
    private final BaranggayService baranggayService;
    private final BaranggayMapper baranggayMapper;

    private final CityService cityService;

    @GetMapping("/{id}")
    public BaranggayDTO getById(@PathVariable("id") int id) {
        Baranggay baranggay = baranggayService.getById(id);
        return baranggayMapper.toDTO(baranggay);
    }

    @GetMapping("/getAllByCity/{cityId}")
    public List<BaranggayDTO> getAllByCity(@PathVariable("cityId") int cityId) {
        City city = cityService.getById(cityId);
        return baranggayService.getAllBy(city).stream()
                .map(baranggayMapper::toDTO)
                .toList();
    }

    @GetMapping("/searchByLocationName")
    public List<BaranggayDTO> searchByLocationName(@RequestParam("locationName") String locationName) {
        return baranggayService.searchByLocationName(locationName).stream()
                .map(baranggayMapper::toDTO)
                .toList();
    }
}
