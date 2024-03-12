package com.elleined.philippinelocationapi.service;

import com.elleined.philippinelocationapi.exception.AlreadyExistsException;
import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;

import java.util.List;

public interface LocationService<ENTITY, DTO> {
    List<ENTITY> saveAll(List<DTO> dtos) throws AlreadyExistsException;
    ENTITY getById(int id) throws ResourceNotFoundException;
    boolean isAlreadyExists(DTO dto);
    List<ENTITY> searchByLocationName(String locationName);
}
