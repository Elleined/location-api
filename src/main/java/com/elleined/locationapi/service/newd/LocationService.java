package com.elleined.locationapi.service.newd;

import com.elleined.locationapi.exception.ResourceNotFoundException;

import java.util.List;

public interface LocationService<ENTITY, DTO> {
    ENTITY save(DTO dto);
    List<ENTITY> saveAll(List<DTO> dtos);
    ENTITY getById(int id) throws ResourceNotFoundException;
    boolean isAlreadyExists(DTO dto);
    boolean isAlreadyExists(List<DTO> dtos);
    List<ENTITY> searchByLocationName(String locationName);
}
