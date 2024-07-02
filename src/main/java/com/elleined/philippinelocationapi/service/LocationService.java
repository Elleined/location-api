package com.elleined.philippinelocationapi.service;

import com.elleined.philippinelocationapi.exception.resource.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.Location;

public interface LocationService<ENTITY extends Location> {
    ENTITY getById(int id) throws ResourceNotFoundException;
}
