package com.elleined.philippinelocationapi.mapper;

import com.elleined.philippinelocationapi.model.PrimaryKeyIdentity;

public interface CustomMapper<ENTITY extends PrimaryKeyIdentity,
        DTO extends com.elleined.philippinelocationapi.dto.DTO> {
    DTO toDTO(ENTITY entity);
}