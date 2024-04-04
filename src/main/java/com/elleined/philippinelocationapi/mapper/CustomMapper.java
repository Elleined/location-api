package com.elleined.philippinelocationapi.mapper;

import com.elleined.philippinelocationapi.model.PrimaryKeyIdentity;
import com.elleined.philippinelocationapi.request.Request;

public interface CustomMapper<ENTITY extends PrimaryKeyIdentity, DTO extends com.elleined.philippinelocationapi.dto.DTO, REQUEST extends Request> {

    DTO toDTO(ENTITY entity);

    ENTITY toEntity(REQUEST request);
}