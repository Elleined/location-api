package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.baranggay.BaranggayDTO;
import com.elleined.philippinelocationapi.mapper.baranggay.BaranggayMapper;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.service.baranggay.BaranggayService;
import com.elleined.philippinelocationapi.service.city.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions/provinces/cities/{cityId}/baranggays")
@RequiredArgsConstructor
public class BaranggayController {
    private final BaranggayService baranggayService;
    private final BaranggayMapper baranggayMapper;

    private final CityService cityService;

    @GetMapping
    public List<BaranggayDTO> getAllByCity(@PathVariable("cityId") int cityId) {
        City city = cityService.getById(cityId);
        return baranggayService.getAllBy(city, ).stream()
                .map(baranggayMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public BaranggayDTO getById(@PathVariable("id") int id) {
        Baranggay baranggay = baranggayService.getById(id);
        return baranggayMapper.toDTO(baranggay);
    }

    @GetMapping("/search")
    public List<BaranggayDTO> searchByLocationName(@RequestParam("name") String name) {
        return baranggayService.findAllByName(name, ).stream()
                .map(baranggayMapper::toDTO)
                .toList();
    }
}
