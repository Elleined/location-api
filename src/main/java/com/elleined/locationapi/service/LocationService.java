package com.elleined.locationapi.service;

import com.elleined.locationapi.exception.ResourceNotFoundException;

import java.util.List;

public interface LocationService<ENTITY, DTO> {
    ENTITY save(DTO dto);
    List<ENTITY> saveAll(List<DTO> dtos);
    ENTITY getById(int id) throws ResourceNotFoundException;
    boolean isAlreadyExists(DTO dto);
    List<ENTITY> searchByLocationName(String locationName);
}
