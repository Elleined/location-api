package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.region.RegionDTO;
import com.elleined.philippinelocationapi.mapper.region.RegionMapper;
import com.elleined.philippinelocationapi.service.region.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;
    private final RegionMapper regionMapper;

    @GetMapping
    public Page<RegionDTO> getAll(@RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                  @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                  @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                  @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                  @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return regionService.getAll(pageable)
                .map(regionMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }

    @GetMapping("/search")
    public Page<RegionDTO> findAllByName(@RequestParam("name") String name,
                                         @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                         @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                         @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                         @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                         @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return regionService.findAllByName(name, pageable)
                .map(regionMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }
}
