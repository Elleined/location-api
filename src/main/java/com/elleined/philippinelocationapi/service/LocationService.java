package com.elleined.philippinelocationapi.service;

import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.PrimaryKeyIdentity;

import java.util.List;

public interface LocationService<ENTITY extends Location> {
    ENTITY save(ENTITY entity);
    List<ENTITY> saveAll(List<ENTITY> entities);
    boolean existsById(int id);
    List<ENTITY> getAll();
    ENTITY getById(int id) throws ResourceNotFoundException;
    List<ENTITY> getAllById(List<Integer> ids);
    List<ENTITY> searchByLocationName(String locationName);
}
