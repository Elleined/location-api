package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.province.ProvinceDTO;
import com.elleined.philippinelocationapi.mapper.province.ProvinceMapper;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.province.ProvinceService;
import com.elleined.philippinelocationapi.service.region.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regions/{regionId}/provinces")
@RequiredArgsConstructor
public class ProvinceController {
    private final ProvinceService provinceService;
    private final ProvinceMapper provinceMapper;

    private final RegionService regionService;

    @GetMapping
    public Page<ProvinceDTO> getAllBy(@PathVariable("regionId") int regionId,
                                      @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                      @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                      @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                      @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                      @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Region region = regionService.getById(regionId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return provinceService.getAllBy(region, pageable)
                .map(provinceMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }

    @GetMapping("/search")
    public Page<ProvinceDTO> findAllByName(@PathVariable("regionId") int regionId,
                                           @RequestParam("name") String name,
                                           @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                           @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                           @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                           @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                           @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Region region = regionService.getById(regionId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return provinceService.findAllByName(region, name, pageable)
                .map(provinceMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }
}
