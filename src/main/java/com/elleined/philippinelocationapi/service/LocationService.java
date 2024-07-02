package com.elleined.philippinelocationapi.service;

import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService<ENTITY extends Location> {
    ENTITY getById(int id) throws ResourceNotFoundException;
    Page<ENTITY> findAllByName(String name, Pageable pageable);
}
